package org.elis.socialnetwork.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private Ruolo ruolo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "utente_followers",
        joinColumns = @JoinColumn(name = "utente_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<Utente> followers;

    @ManyToMany(mappedBy = "followers", fetch = FetchType.LAZY)
    private List<Utente> following;


    // RELAZIONE POST

    //Un utente puo creare Many post TEST
    @OneToMany(mappedBy = "utente", fetch = FetchType.LAZY)
    private List<Post> posts;

    //Molti utenti possono Lasciare like a molti post
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> postsLiked;

    @OneToMany(mappedBy = "utente", fetch = FetchType.LAZY)
    private List<Commenti> commenti;


    public Utente(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ruolo.toString()));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(id, utente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
