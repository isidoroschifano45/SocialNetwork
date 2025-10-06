package org.elis.socialnetwork.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.post.PostCreateDTO;
import org.elis.socialnetwork.dto.response.post.PostResponseDTO;
import org.elis.socialnetwork.mapper.post.PostMapper;
import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.security.config.JwtUtilities;
import org.elis.socialnetwork.service.PostService;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Controller dei POST", description = "API per la gestione dei post")
public class PostController {


    private final PostService postService;
    private final PostMapper postMapper;
    private final JwtUtilities jwtUtilities;

    // - - - - - TUTTI I GET - - - - -


    // - - - - - TUTTI I POST - - - - -
    @PostMapping("crea")
    public ResponseEntity<PostResponseDTO> creaPost(@Valid @RequestBody PostCreateDTO post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // es. email o username dell’utente
        Post postCreato = postService.createPost(post, username);
        return ResponseEntity.ok().body(postMapper.convertToDTO(postCreato));
    }







}
