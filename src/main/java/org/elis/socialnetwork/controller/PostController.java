package org.elis.socialnetwork.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.post.PostCreateDTO;
import org.elis.socialnetwork.dto.response.post.PostResponseDTO;
import org.elis.socialnetwork.mapper.post.PostMapper;
import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.model.Utente;
import org.elis.socialnetwork.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Controller dei POST", description = "API per la gestione dei post")
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    // - - - - - TUTTI I GET - - - - -
    @Operation(summary = "Ottieni la lista dei post di un utente")
    @GetMapping("/posts/{idUtente}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByIdUtente(@PathVariable Long idUtente){
        List<PostResponseDTO> postsUtente = postService.findPostsByUtenteId(idUtente).stream()
                .map(postMapper::convertToDTO)
                .toList();

        return ResponseEntity.ok().body(postsUtente);

    }

    // - - - - - TUTTI I POST - - - - -
    @Operation(summary = "Crea un post")
    @PostMapping("/posts")
    public ResponseEntity<PostResponseDTO> creaPost(@Valid @RequestBody PostCreateDTO post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Post postCreato = postService.createPost(post, username);
        return ResponseEntity.ok().body(postMapper.convertToDTO(postCreato));
    }

    // - - - - - TUTTI I PATCH - - - - -
    @Operation(summary = "Modifica il post")
    @PostMapping("/posts/modifica/{idPost}")
    public ResponseEntity<PostResponseDTO> modificaPost(@PathVariable Long idPost,@Valid @RequestBody PostCreateDTO post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Post postModificato = postService.updatePost(idPost , post , username);

        return  ResponseEntity.ok().body(postMapper.convertToDTO(postModificato));
        
    }


    // - - - - - TUTTI I DELETE - - - - -\\
    @Operation(summary = "Cancella il post") //TODO:da VERIFICARE L'ECCEZIONE
    @DeleteMapping("/posts/cancella/{idPost}")
    public ResponseEntity<Void> cancellaPost(@PathVariable Long idPost) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        postService.deletePostById(idPost , username);

        return ResponseEntity.ok().build();
    }


}
