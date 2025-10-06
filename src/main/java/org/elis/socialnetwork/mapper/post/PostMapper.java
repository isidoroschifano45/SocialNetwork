package org.elis.socialnetwork.mapper.post;

import org.elis.socialnetwork.dto.request.post.PostCreateDTO;
import org.elis.socialnetwork.dto.response.post.PostResponseDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;
import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.model.Utente;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostMapper {

    public Post createPost(PostCreateDTO postCreato) {
        Post post = new Post();
        post.setTesto(postCreato.getTesto());
        post.setDataEOra(postCreato.getDataEOra() != null ? postCreato.getDataEOra() : LocalDateTime.now());

        return post;
    }

    public PostResponseDTO convertToDTO(Post post) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setId(post.getId());
        postResponseDTO.setTesto(post.getTesto());
        postResponseDTO.setDataEOra(post.getDataEOra());
        postResponseDTO.setLikes(post.getUtentiCheHannoMessoLike() != null ? post.getUtentiCheHannoMessoLike().size() : 0);

        return postResponseDTO;
    }

}
