package org.elis.socialnetwork.service;

import org.elis.socialnetwork.dto.request.utente.UtenteUpdateDTO;
import org.elis.socialnetwork.model.Utente;

import java.util.List;

public interface UtenteService {

    Utente findById(Long id);
    Utente findUtenteByUsername(String username);
    Utente saveUtente(Utente u);
    void deleteUtenteById(Long id);
    List<Utente> findAll();
    Utente updateUtenteById(Long id, UtenteUpdateDTO u);
    Utente addFollowing(Long id, Long idFollowing);
    Utente removeFollowing(Long id, Long idFollowing);
    Utente removeFollower(Long id, Long idFollower);


}
