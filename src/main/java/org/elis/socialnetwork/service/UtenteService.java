package org.elis.socialnetwork.service;

import org.elis.socialnetwork.dto.request.utente.LoginDTO;
import org.elis.socialnetwork.dto.request.utente.UtenteUpdateDTO;
import org.elis.socialnetwork.model.Utente;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UtenteService extends UserDetailsService {

    Utente findById(Long id);
    Utente findUtenteByUsername(String username);
    @Deprecated
    Utente saveUtente(Utente u);
    void deleteUtenteById(Long id);
    List<Utente> findAll();
    Utente updateUtenteById(Long id, UtenteUpdateDTO u);
    Utente addFollowing(String usernameMain , String usernameFollowing);
    Utente removeFollowing(String usernameMain , String usernameFollowing);
    Utente removeFollower(String usernameMain , String usernameFollower);
    Utente registraUtente(Utente u);
    Utente login(LoginDTO u);

}
