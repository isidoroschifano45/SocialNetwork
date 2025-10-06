package org.elis.socialnetwork.service.impl;

import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
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
    public Post savePost(Post post) {
        return null;
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
