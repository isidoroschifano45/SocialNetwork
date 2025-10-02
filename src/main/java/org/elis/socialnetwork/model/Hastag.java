package org.elis.socialnetwork.model;

import jakarta.persistence.*;

@Entity
public class Hastag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=false)
    private String nome;
}
