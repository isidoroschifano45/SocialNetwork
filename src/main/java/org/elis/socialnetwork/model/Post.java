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

    @Column(nullable=true, unique=false)
    private String DataUltimaModifica;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_hastag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "hastag_id")
    )
    private List<Hastag> hastag;




    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToMany(mappedBy = "postsLiked", fetch = FetchType.LAZY)
    private List<Utente> utentiCheHannoMessoLike;

    public Post(){

    }

}
