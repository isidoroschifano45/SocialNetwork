package org.elis.socialnetwork.model;

import jakarta.persistence.*;

@Entity
public class Messaggi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique=false)
    private String dataEOra;

    @Column(nullable=false, unique=false)
    private String testo;

    @Column(nullable=false, unique=true)
    private Long IdMittente;
}
