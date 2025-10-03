package org.elis.socialnetwork.service.impl;


import org.elis.socialnetwork.dto.request.utente.UtenteUpdateDTO;
import org.elis.socialnetwork.exception.utente.UtenteNotFoundException;
import org.elis.socialnetwork.model.Utente;
import org.elis.socialnetwork.repository.UtenteRepository;
import org.elis.socialnetwork.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository utenteRepo;

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
    public List<Utente> findFollowers(Long id) {

        List<Utente> followers = utenteRepo.findById(id).orElseThrow(()->new UtenteNotFoundException("Utente con: " + id+ " non trovato")).getFollowers();
        return followers;
    }

    @Override
    public List<Utente> findFollowing(Long id) {
        List<Utente> following = utenteRepo.findById(id).orElseThrow(()->new UtenteNotFoundException("Utente con: " + id + " non trovato")).getFollowing();
        return following;
    }


}
