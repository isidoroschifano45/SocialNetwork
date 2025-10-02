package org.elis.socialnetwork.model;

import jakarta.persistence.*;

@Entity
public class Commenti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique=false)
    private String dataEOra;

    @Column(nullable=false, unique=false)
    private String testo;
}
