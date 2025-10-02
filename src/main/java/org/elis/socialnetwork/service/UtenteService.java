package org.elis.socialnetwork.service;

import org.elis.socialnetwork.model.Utente;

import java.util.List;

public interface UtenteService {

    Utente findById(Long id);
    Utente findUtenteByUsername(String username);
    Utente saveUtente(Utente u);
    void deleteUtenteById(Long id);
    List<Utente> findAll();
    Utente updateUtenteById(Long id, Utente u);
    List<Utente> findFollowers(Long id);
    List<Utente> findFollowing(Long id);

}
