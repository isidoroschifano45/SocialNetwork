package org.elis.socialnetwork.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=false)
    private String testo;

    @Column(nullable = false, unique=false)
    private String dataEOra;

    @Column(nullable=false, unique=false)
    private String DataUltimaModifica;

    /*
    @Column()
    @ManyToMany(MappedBy = "hastag", fetch=FetchType.LAZY)
    private List<Hastag> hastag;
    */

    
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToMany(mappedBy = "postsLiked", fetch = FetchType.LAZY)
    private List<Utente> utentiCheHannoMessoLike;

}
