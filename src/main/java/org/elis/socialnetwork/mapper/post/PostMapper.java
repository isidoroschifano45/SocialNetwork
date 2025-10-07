package org.elis.socialnetwork.mapper.post;

import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.post.PostCreateDTO;
import org.elis.socialnetwork.dto.response.post.PostResponseDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;
import org.elis.socialnetwork.mapper.CommentiMapper;
import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.model.Utente;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final CommentiMapper commentiMapper;

    public Post createPost(PostCreateDTO postCreato) {
        Post post = new Post();
        post.setTesto(postCreato.getTestoPost());
        post.setDataEOra(postCreato.getDataEOra() != null ? postCreato.getDataEOra() : LocalDateTime.now());

        return post;
    }

    public PostResponseDTO convertToDTO(Post post) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setIdPost(post.getId());
        postResponseDTO.setTestoPost(post.getTesto());
        postResponseDTO.setDataEOra(post.getDataEOra());
        postResponseDTO.setLikes(post.getUtentiCheHannoMessoLike() != null ? post.getUtentiCheHannoMessoLike().size() : 0);
        postResponseDTO.setLink(post.getLink());
        postResponseDTO.setCommenti(
            post.getCommenti() != null
                ? post.getCommenti().stream()
                    .map(commentiMapper::convertToDTO)
                    .toList()
                : null
        );

        return postResponseDTO;
    }

}
