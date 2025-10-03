package org.elis.socialnetwork.mapper.post;

import org.elis.socialnetwork.dto.request.post.PostCreateDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;
import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.model.Utente;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostMapper {

    public Post createPost(PostCreateDTO postCreato, Utente utente) {
        Post post = new Post();
        post.setTesto(postCreato.getTesto());
        post.setDataEOra(postCreato.getDataEOra() != null ? postCreato.getDataEOra() : LocalDateTime.now());
        post.setUtente(utente);
        return post;
    }

}
