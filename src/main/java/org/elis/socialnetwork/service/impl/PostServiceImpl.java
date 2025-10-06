package org.elis.socialnetwork.service.impl;

import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.post.PostCreateDTO;
import org.elis.socialnetwork.exception.utente.UtenteNotFoundException;
import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.model.Utente;
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

    @Override
    public Post findPostById(Long postId) {
            return null;
    }


    public List<Post> findAllPosts() {
        return List.of();
    }

    @Override
    public List<Post> findPostsByUtenteId(Long utenteId) {
        return List.of();
    }

    @Override
    public Post createPost(PostCreateDTO post , String nomeUtente) {
            Utente u = utenteRepo.findUtenteByUsername(nomeUtente).orElseThrow(()->new UtenteNotFoundException("Utente con username: "+nomeUtente+" non trovato"));
            Post nuovoPost = new Post();
            nuovoPost.setTesto(post.getTesto());
            nuovoPost.setDataEOra(post.getDataEOra());
            nuovoPost.setUtentiCheHannoMessoLike(new ArrayList<>());
            nuovoPost.setUtente(u);

            return postRepo.save(nuovoPost);

    }


    @Override
    public Post updatePost(Long id, Post post) {
        return null;
    }

    @Override
    public void deletePostById(Long id) {

    }

    @Override
    public void likePost(Long postId, Long utenteId) {

    }

    @Override
    public void unlikePost(Long postId, Long utenteId) {

    }
}
