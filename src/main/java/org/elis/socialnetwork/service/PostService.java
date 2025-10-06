package org.elis.socialnetwork.service;

import org.elis.socialnetwork.dto.request.post.PostCreateDTO;
import org.elis.socialnetwork.dto.response.post.PostResponseDTO;
import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.model.Utente;

import java.util.List;

public interface PostService {
    Post findPostById(Long id);
    List<Post> findPostsByUtenteId(Long idUtente);
    Post createPost(PostCreateDTO post, String username);
    Post updatePost(Long idPost, PostCreateDTO post, String username);
    void deletePostById(Long idPost , String username);
    void likePost(Long postId, Long utenteId);
    void unlikePost(Long postId, Long utenteId);



}



