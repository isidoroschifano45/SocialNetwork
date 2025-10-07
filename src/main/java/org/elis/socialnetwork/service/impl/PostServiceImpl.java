package org.elis.socialnetwork.service.impl;

import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.post.PostCreateDTO;
import org.elis.socialnetwork.exception.post.PostNotAllowed;
import org.elis.socialnetwork.exception.post.PostNotFoundException;
import org.elis.socialnetwork.exception.utente.UtenteNotFoundException;
import org.elis.socialnetwork.model.Hastag;
import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.model.Utente;
import org.elis.socialnetwork.repository.HastagRepository;
import org.elis.socialnetwork.repository.PostRepository;
import org.elis.socialnetwork.repository.UtenteRepository;
import org.elis.socialnetwork.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepo;
    private final UtenteRepository  utenteRepo;
    private final HastagRepository hastagRepo;

    @Override
    public Post findPostById(Long postId) {
            return null;
    }

    @Override
    public List<Post> findAllPosts() {
        return List.of();
    }

    @Override
    public List<Post> findPostsByUtenteId(Long idUtente) {

        Utente u = utenteRepo.findById(idUtente).orElseThrow(()->new UtenteNotFoundException("Utente con id: "+idUtente+" non trovato"));
        return u.getPosts();

    }

    @Override
    public Post createPost(PostCreateDTO post, String nomeUtente) {
        Utente u = utenteRepo.findUtenteByUsername(nomeUtente)
                .orElseThrow(() -> new UtenteNotFoundException("Utente con username: " + nomeUtente + " non trovato"));
        Post nuovoPost = new Post();
        nuovoPost.setTesto(post.getTesto());
        nuovoPost.setDataEOra(post.getDataEOra());
        nuovoPost.setUtentiCheHannoMessoLike(new ArrayList<>());
        nuovoPost.setUtente(u);
        nuovoPost.setLink(post.getLink());

        List<String> hastags = post.getHastags();
        List<Hastag> listaHastag = new ArrayList<>();
        for (String hastag : hastags) {
            Hastag aggiungiHastag = hastagRepo.getByNome(hastag);
            if (aggiungiHastag == null) {
                aggiungiHastag = new Hastag();
                aggiungiHastag.setNome(hastag);
                aggiungiHastag = hastagRepo.save(aggiungiHastag); // salvo nel db
            }
            listaHastag.add(aggiungiHastag); // aggiungo alla lista
        }
        nuovoPost.setHastag(listaHastag);

        return postRepo.save(nuovoPost);
    }


    @Override
    public Post updatePost(Long idPost, PostCreateDTO post, String username) {
        Utente u = utenteRepo.findUtenteByUsername(username).orElseThrow(()->new UtenteNotFoundException("Utente con username: "+username+" non trovato"));
        Post postModificato = postRepo.findById(idPost).orElseThrow(()->new PostNotFoundException("Post con id: "+idPost+" non trovato"));
        if(!postModificato.getUtente().getEmail().equals(u.getEmail())){

            throw new PostNotAllowed("Non sei il proprietario di questo post");
        }
        postModificato.setTesto(post.getTesto());
        postModificato.setDataEOra(post.getDataEOra());


        return postRepo.save(postModificato);
    }

    @Override
    public void deletePostById(Long idPost, String username) {

        Utente u = utenteRepo.findUtenteByUsername(username).orElseThrow(() -> new UtenteNotFoundException("Utente con username: " + username + " non trovato"));
        Post postDaCancellare = postRepo.findById(idPost).orElseThrow(() -> new PostNotFoundException("Post con id: " + idPost + " non trovato"));
        if (!postDaCancellare.getUtente().getEmail().equals(u.getEmail())) {
            throw new PostNotAllowed("Non sei il proprietario di questo post");
        }
        postRepo.delete(postDaCancellare);
    }

    @Override
    public void likePost(Long postId, String username) {
        Utente utenteMetteLike = utenteRepo.findUtenteByUsername(username)
                .orElseThrow(() -> new UtenteNotFoundException("Utente con username: " + username + " non trovato"));
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post con id: " + postId + " non trovato"));

        if (!post.getUtentiCheHannoMessoLike().contains(utenteMetteLike)) {
            System.out.println("prova");
            post.getUtentiCheHannoMessoLike().add(utenteMetteLike);
            utenteMetteLike.getPostsLiked().add(post);
            postRepo.save(post);

        }

    }


    @Override
    public void unlikePost(Long postId, String username) {

        Utente utenteToglieLike = utenteRepo.findUtenteByUsername(username)
                .orElseThrow(() -> new UtenteNotFoundException("Utente con username: " + username + " non trovato"));
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post con id: " + postId + " non trovato"));

        if (post.getUtentiCheHannoMessoLike().contains(utenteToglieLike)) {
            post.getUtentiCheHannoMessoLike().remove(utenteToglieLike);
            utenteToglieLike.getPostsLiked().remove(post);
            postRepo.save(post);
        }


    }
}
