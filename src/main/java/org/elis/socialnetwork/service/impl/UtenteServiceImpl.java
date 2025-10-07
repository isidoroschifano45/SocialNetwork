package org.elis.socialnetwork.service.impl;


import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.utente.LoginDTO;
import org.elis.socialnetwork.dto.request.utente.UtenteUpdateDTO;
import org.elis.socialnetwork.exception.utente.UtenteAlreadyFollowed;
import org.elis.socialnetwork.exception.utente.UtenteNotFoundException;
import org.elis.socialnetwork.model.Ruolo;
import org.elis.socialnetwork.model.Utente;
import org.elis.socialnetwork.repository.UtenteRepository;
import org.elis.socialnetwork.service.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UtenteServiceImpl implements UtenteService {


    private final UtenteRepository utenteRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Utente findById(Long id) {
         return utenteRepo.findById(id).orElseThrow(()->new UtenteNotFoundException("Utente con id: "+id+" non trovato"));
    }

    @Override
    public Utente findUtenteByUsername(String username) {
       return utenteRepo.findUtenteByUsername(username).orElseThrow(()->new UtenteNotFoundException("Utente con username: "+username+" non trovato"));
    }

    @Override
    public Utente saveUtente(Utente u) {
        return utenteRepo.save(u);
    }

    @Override
    public void deleteUtenteById(Long id) {
        Utente u = utenteRepo.findById(id).orElseThrow(()->new UtenteNotFoundException("Utente con id: "+id+" non trovato"));
        utenteRepo.delete(u);
    }

    @Override
    public List<Utente> findAll() {
        return utenteRepo.findAll();
    }

    @Override
    public Utente updateUtenteById(Long id, UtenteUpdateDTO u) {
        Utente daModificare = utenteRepo.findById(id).orElseThrow(()->new UtenteNotFoundException("Utente con id: "+id+" non trovato"));
        daModificare.setUsername(u.getUsername());

        return utenteRepo.save(daModificare);

    }

    @Override
    public Utente addFollowing(String usernameMain, String usernameFollowing) {
        Utente utenteMain = utenteRepo.findUtenteByUsername(usernameMain).orElseThrow(()->new UtenteNotFoundException("Utente con username: "+usernameMain+" non trovato"));
        Utente utenteSeguito = utenteRepo.findUtenteByUsername(usernameFollowing).orElseThrow(()->new UtenteNotFoundException("Utente con username: "+usernameFollowing+" non trovato"));
        if(utenteMain.getUsername().equals(utenteSeguito.getUsername())){
            throw new UtenteAlreadyFollowed("Non puoi seguire te stesso");
        }
        if(utenteMain.getFollowing().contains(utenteSeguito)){
            throw new UtenteAlreadyFollowed("Utente con username: "+usernameFollowing+" già seguito");
        }
        utenteMain.getFollowing().add(utenteSeguito);
        utenteSeguito.getFollowers().add(utenteMain);

        utenteRepo.save(utenteSeguito);

        return utenteRepo.save(utenteMain);

    }

    @Override
    public Utente removeFollowing(String usernameMain, String usernameFollowing) {
        Utente utenteMain = utenteRepo.findUtenteByUsername(usernameMain).orElseThrow(()->new UtenteNotFoundException("Utente con username: "+usernameMain+" non trovato"));
        Utente utenteSeguito = utenteRepo.findUtenteByUsername(usernameFollowing).orElseThrow(()->new UtenteNotFoundException("Utente con username: "+usernameFollowing+" non trovato"));
        if(utenteMain.getUsername().equals(utenteSeguito.getUsername())){
            throw new UtenteAlreadyFollowed("Non puoi smettere di seguire te stesso");
        }

        if(!utenteMain.getFollowing().contains(utenteSeguito)){
            throw new UtenteNotFoundException("Utente con username: " + usernameFollowing + " non è nella lista dei seguiti");
        }
        utenteMain.getFollowing().remove(utenteSeguito);
        utenteSeguito.getFollowers().remove(utenteMain);

        utenteRepo.save(utenteSeguito);

        return utenteRepo.save(utenteMain);
    }

    @Override
    public Utente removeFollower(String usernameMain, String usernameFollower) {
        Utente utenteMain = utenteRepo.findUtenteByUsername(usernameMain).orElseThrow(()->new UtenteNotFoundException("Utente con username: "+usernameMain+" non trovato"));
        Utente utenteFollower = utenteRepo.findUtenteByUsername(usernameFollower).orElseThrow(()->new UtenteNotFoundException("Utente con username: "+usernameFollower+" non trovato"));
        if(utenteMain.getUsername().equals(utenteFollower.getUsername())) {
            throw new UtenteAlreadyFollowed("Non puoi togliere te stesso dalla lista dei followers");
        }

        if(!utenteMain.getFollowing().contains(utenteFollower)){
            throw new UtenteNotFoundException("Utente con id: " + utenteFollower + " non è nella lista di coloro che ti seguono");
        }
        utenteMain.getFollowers().remove(utenteFollower);
        utenteFollower.getFollowing().remove(utenteMain);

        utenteRepo.save(utenteFollower);

        return utenteRepo.save(utenteMain);
    }

    @Override
    public Utente registraUtente(Utente u) {

        String passwordCifrata = passwordEncoder.encode(u.getPassword());
        u.setPassword(passwordCifrata);
        u.setRuolo(Ruolo.USER);

        return utenteRepo.save(u);
    }

    @Override
    public Utente login(LoginDTO u) {
        Utente utenteLoggato = utenteRepo.findUtenteByEmail(u.getEmail());
        if(!passwordEncoder.matches(u.getPassword(), utenteLoggato.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password errata");
        }
        return utenteLoggato;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utenteRepo.findUtenteByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("utente con username "+username+" non trovato"));
    }


}
