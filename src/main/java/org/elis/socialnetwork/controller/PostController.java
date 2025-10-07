package org.elis.socialnetwork.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.commenti.CommentoCreateDTO;
import org.elis.socialnetwork.dto.request.commenti.CommentoUpdateDTO;
import org.elis.socialnetwork.dto.request.post.PostCreateDTO;
import org.elis.socialnetwork.dto.response.post.PostResponseDTO;
import org.elis.socialnetwork.mapper.post.PostMapper;
import org.elis.socialnetwork.model.Commenti;
import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.model.Utente;
import org.elis.socialnetwork.service.CommentiService;
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
    private final CommentiService commentiService;

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

    @PostMapping("/posts/like/{idPost}")
    public ResponseEntity<Void> likePost(@PathVariable Long idPost) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        postService.likePost(idPost, username);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Togliere 'mi piace' ad un post")
    @PostMapping("/posts/unlike/{idPost}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long idPost) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        postService.unlikePost(idPost, username);
        return ResponseEntity.ok().build();

    }

    @Operation(summary = "Commenta un post")
    @PostMapping("/posts/commenta/{idPost}")
    public ResponseEntity<Void> commentaPost(@PathVariable Long idPost, @RequestBody CommentoCreateDTO commento) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        commentiService.creaCommento(commento , username ,  idPost);


        return ResponseEntity.ok().build();

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

    @Operation(summary = "Modifica il commento")
    @PatchMapping("/posts/{idPost}/modificacommento/{idCommento}")
    public ResponseEntity<Void> modificaCommento(
            @PathVariable Long idPost,
            @PathVariable Long idCommento,
            @Valid @RequestBody CommentoUpdateDTO commento) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        commentiService.updateCommento(commento, username, idPost, idCommento);
        return ResponseEntity.ok().build();
    }





    // - - - - - TUTTI I DELETE - - - - -\\
    @Operation(summary = "Cancella il post")
    @DeleteMapping("/posts/cancella/{idPost}")
    public ResponseEntity<Void> cancellaPost(@PathVariable Long idPost) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        postService.deletePostById(idPost , username);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Cancella il commento")
    @DeleteMapping("/posts/{idPost}/removecommento/{idCommento}")
    public ResponseEntity<Void> cancellaCommento(@PathVariable Long idPost , @PathVariable Long idCommento) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        commentiService.deleteCommento(idCommento , username ,   idPost);

        return ResponseEntity.ok().build();
    }


}
