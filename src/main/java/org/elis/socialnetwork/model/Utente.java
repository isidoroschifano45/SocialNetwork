package org.elis.socialnetwork.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

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

    //Un utente puo creare Many post
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


    public Utente(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Utente> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Utente> followers) {
        this.followers = followers;
    }

    public List<Utente> getFollowing() {
        return following;
    }

    public void setFollowing(List<Utente> following) {
        this.following = following;
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
