package org.elis.socialnetwork.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=true, unique=true)
    private String nome;

}
