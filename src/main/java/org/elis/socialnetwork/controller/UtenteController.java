package org.elis.socialnetwork.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.post.PostCreateDTO;
import org.elis.socialnetwork.dto.request.utente.LoginDTO;
import org.elis.socialnetwork.dto.request.utente.UtenteRegisterDTO;
import org.elis.socialnetwork.dto.request.utente.UtenteUpdateDTO;
import org.elis.socialnetwork.dto.response.post.PostResponseDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteWithFollowDTO;
import org.elis.socialnetwork.mapper.post.PostMapper;
import org.elis.socialnetwork.mapper.utente.UtenteMapper;
import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.model.Utente;
import org.elis.socialnetwork.security.config.JwtUtilities;
import org.elis.socialnetwork.service.PostService;
import org.elis.socialnetwork.service.UtenteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

// I Controller gestiscono le richieste HTTP, espongono le API.
@RestController
@RequiredArgsConstructor
@Tag(name = "Controller degli UTENTI", description = "API per la gestione degli utenti")
public class UtenteController {

    // I Service gestiscono la logica di business e l'interazione con il database
    private final UtenteService utenteService;

    // I Mapper gestiscono la conversione tra entità e DTO
    private final UtenteMapper utenteMapper;

    private final PostService postService;
    private final PostMapper postMapper;

    private final JwtUtilities jwtUtilities;

    // - - - - - TUTTI I GET - - - - -
    @Operation(summary = "Ottieni la lista di tutti gli utenti")
    @GetMapping("/utenti")
    public ResponseEntity<List<UtenteResponseDTO>> getUtenti(){

        return ResponseEntity.ok().body(utenteService.findAll().stream()
                .map(utenteMapper::convertToDTO)
                .toList());
    }

    @Operation(summary = "Ottieni un utente dall'id")
    @GetMapping("/utenti/{id}")
    public ResponseEntity<UtenteWithFollowDTO> getUtenteById(@PathVariable Long id){
        System.out.println("Visualizza");
       Utente utenteById =  utenteService.findById(id);
        return ResponseEntity.ok().body(utenteMapper.convertToDTOWithFollow(utenteById));
    }

    @Operation(summary = "Ottieni un utente dallo username")
    @GetMapping("/utenti/username/{username}")
    public ResponseEntity<UtenteResponseDTO> findUtenteByUsername(@PathVariable String username){

       Utente utenteByUsername =  utenteService.findUtenteByUsername(username);

       return ResponseEntity.ok().body(utenteMapper.convertToDTO(utenteByUsername));

    }

    // - - - - - TUTTI I POST - - - - -

    @Operation(summary = "Registra un utente")
    @PostMapping("/registrazione")
    public ResponseEntity<UtenteResponseDTO> registraUtente(@Valid @RequestBody UtenteRegisterDTO u){

        Utente utenteSalvato = utenteService.registraUtente(utenteMapper.formInsertUtente(u));

        return ResponseEntity.ok().body(utenteMapper.convertToDTO(utenteSalvato));
    }

    @Operation(summary = "Segui un utente")
    @PostMapping("/utenti/{id}/segue/{idFollowing}")
    public ResponseEntity<UtenteWithFollowDTO> seguiUtente(@PathVariable Long id, @PathVariable Long idFollowing){

        Utente utenteSeguito = utenteService.addFollowing(id, idFollowing);

        return ResponseEntity.ok().body(utenteMapper.convertToDTOWithFollow(utenteSeguito));
    }

    @Operation(summary = "Smetti di seguire un utente")
    @PostMapping("/utenti/{id}/removeFollowing/{idFollowing}")
    public ResponseEntity<UtenteWithFollowDTO> removeFollowing(@PathVariable Long id, @PathVariable Long idFollowing){

        Utente utenteSeguito = utenteService.removeFollowing(id, idFollowing);

        return ResponseEntity.ok().body(utenteMapper.convertToDTOWithFollow(utenteSeguito));
    }

    @Operation(summary = "Togli un follower")
    @PostMapping("/utenti/{id}/removeFollower/{idFollower}")
    public ResponseEntity<UtenteWithFollowDTO> removeFollower(@PathVariable Long id, @PathVariable Long idFollower){
            Utente utenteSeguito = utenteService.removeFollower(id, idFollower);

            return ResponseEntity.ok().body(utenteMapper.convertToDTOWithFollow(utenteSeguito));
    }

    @Operation(summary = "Faccio il login")
    @PostMapping("/login")
    public ResponseEntity<UtenteResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO){

        Utente login = utenteService.login(loginDTO);
        String token = jwtUtilities.generaToken(login);

        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.AUTHORIZATION, token).body(utenteMapper.convertToDTO(login));


    }




    // - - - - - TUTTI I PATCH - - - - -

    @Operation(summary = "Aggiorna un utente dall'id")
    @PatchMapping("/utenti/{id}")
    public ResponseEntity<UtenteResponseDTO> updateUtenteById(@PathVariable Long id , @RequestBody UtenteUpdateDTO u){
        utenteService.updateUtenteById(id , u);
        return ResponseEntity.ok().build();
    }

    // - - - - - TUTTI I DELETE - - - - -

    @Operation(summary = "Cancella un utente dall'id")
    @DeleteMapping("/utenti/{id}")
    public ResponseEntity deleteUtenteById(@PathVariable Long id){

        utenteService.deleteUtenteById(id);
        return ResponseEntity.ok().build();
    }
}
