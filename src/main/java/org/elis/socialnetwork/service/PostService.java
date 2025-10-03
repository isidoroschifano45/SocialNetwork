package org.elis.socialnetwork.service;

import org.elis.socialnetwork.model.Post;

import java.util.List;

public interface PostService {
    Post findPostById(Long id);
    List<Post> findPostsByUtenteId(Long utenteId);
    Post savePost(Post post);
    Post updatePost(Long id, Post post);
    void deletePostById(Long id);
    void likePost(Long postId, Long utenteId);
    void unlikePost(Long postId, Long utenteId);



}



