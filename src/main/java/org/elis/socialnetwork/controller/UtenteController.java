package org.elis.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.utente.UtenteRegisterDTO;
import org.elis.socialnetwork.dto.request.utente.UtenteUpdateDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;
import org.elis.socialnetwork.mapper.utente.UtenteMapper;
import org.elis.socialnetwork.model.Utente;
import org.elis.socialnetwork.service.UtenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UtenteController {


    private final UtenteService utenteService; //questo comunica con il db
    private final UtenteMapper utenteMapper;// questo invece converte da entità a DTO e viceversa

    //ottengo la lista di tutti gli utenti
    @GetMapping("/utenti")
    public ResponseEntity<List<UtenteResponseDTO>> getUtenti(){

        return ResponseEntity.ok().body(utenteService.findAll().stream()
                .map(utenteMapper::convertToDTO)
                .toList());
    }

    //ottengo l'utente tramite l'id
    @GetMapping("/utenti/{id}")
    public ResponseEntity<UtenteResponseDTO> getUtenteById(@PathVariable Long id){

       Utente utenteById =  utenteService.findById(id);
        return ResponseEntity.ok().body(utenteMapper.convertToDTO(utenteById));
    }

    //trovo un utente dall'username
    @GetMapping("/utenti/username/{username}")
    public ResponseEntity<UtenteResponseDTO> findUtenteByUsername(@PathVariable String username){

       Utente utenteByUsername =  utenteService.findUtenteByUsername(username);
       return ResponseEntity.ok().body(utenteMapper.convertToDTO(utenteByUsername));

    }

    //inserisco l'utente
    @PostMapping("/utenti")
    public ResponseEntity<UtenteResponseDTO> insertUtente(@RequestBody UtenteRegisterDTO u){

        Utente utenteSalvato = utenteService.saveUtente(utenteMapper.formInsertUtente(u));

        return ResponseEntity.ok().body(utenteMapper.convertToDTO(utenteSalvato));
    }

    //modifico un utente
    @PatchMapping("/utenti/{id}")
    public ResponseEntity<UtenteResponseDTO> updateUtenteById(@PathVariable Long id , @RequestBody UtenteUpdateDTO u){
        utenteService.updateUtenteById(id , u);
        return ResponseEntity.ok().build();
    }

    //elimino un utente tramite l'id
    @DeleteMapping("/utenti/{id}")
    public ResponseEntity deleteUtenteById(@PathVariable Long id){

        utenteService.deleteUtenteById(id);
        return ResponseEntity.ok().build();
    }








}
