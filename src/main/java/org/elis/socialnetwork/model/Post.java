package org.elis.socialnetwork.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=false)
    private String testo;

    @Column(nullable = false, unique=false)
    private LocalDateTime dataEOra;

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

    public Post(){

    }

}
