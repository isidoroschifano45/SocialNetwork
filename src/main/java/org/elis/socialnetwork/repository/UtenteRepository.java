package org.elis.socialnetwork.repository;

import org.elis.socialnetwork.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository <Utente, Long>{

    Optional<Utente> findUtenteByUsername(String username);

}

