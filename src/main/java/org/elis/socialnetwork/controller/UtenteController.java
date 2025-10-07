package org.elis.socialnetwork.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.utente.UtenteUpdateDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteWithFollowDTO;
import org.elis.socialnetwork.mapper.utente.UtenteMapper;
import org.elis.socialnetwork.model.Utente;
import org.elis.socialnetwork.service.UtenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Operation(summary = "Segui un utente")
    @PostMapping("/utenti/segue/{usernameFollowing}")
    public ResponseEntity<UtenteWithFollowDTO> seguiUtente(@PathVariable String usernameFollowing){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameMain = authentication.getName();
        Utente utenteSeguito = utenteService.addFollowing(usernameMain ,usernameFollowing);

        return ResponseEntity.ok().body(utenteMapper.convertToDTOWithFollow(utenteSeguito));
    }

    @Operation(summary = "Smetti di seguire un utente")
    @PostMapping("/utenti/removeFollowing/{usernameFollowing}")
    public ResponseEntity<UtenteWithFollowDTO> removeFollowing(@PathVariable String usernameFollowing){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameMain = authentication.getName();
        Utente utenteSeguito = utenteService.removeFollowing(usernameMain ,usernameFollowing);

        return ResponseEntity.ok().body(utenteMapper.convertToDTOWithFollow(utenteSeguito));
    }

    @Operation(summary = "Togli un follower")
    @PostMapping("/utenti/removeFollower/{usernameFollower}")
    public ResponseEntity<UtenteWithFollowDTO> removeFollower(@PathVariable String usernameFollower){

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String usernameMain = authentication.getName();
            Utente utenteFollower = utenteService.removeFollower(usernameMain ,usernameFollower);

            return ResponseEntity.ok().body(utenteMapper.convertToDTOWithFollow(utenteFollower));
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
