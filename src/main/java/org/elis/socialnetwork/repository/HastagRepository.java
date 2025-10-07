package org.elis.socialnetwork.repository;

import org.elis.socialnetwork.model.Hastag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HastagRepository extends JpaRepository<Hastag,Long> {
    Hastag getByNome(String nome);
}
