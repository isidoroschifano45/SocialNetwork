package org.elis.socialnetwork.controller;

import org.elis.socialnetwork.model.Utente;
import org.elis.socialnetwork.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    //ottengo la lista di tutti gli utenti
    @GetMapping("/utenti")
    public ResponseEntity<List<Utente>> getUtenti(){
        return ResponseEntity.ok().body(utenteService.findAll());
    }

    //ottengo l'utente tramite l'id
    @GetMapping("/utenti/{id}")
    public ResponseEntity<Utente> getUtenteById(Long id){
        return ResponseEntity.ok().body(utenteService.findById(id));
    }

    //trovo un utente dall'username
    @GetMapping("/utenti/{username}")
    public ResponseEntity<Utente> findUtenteByUsername(@PathVariable String username){
        return ResponseEntity.ok().body(utenteService.findUtenteByUsername(username));
    }

    //inserisco l'utente
    @PostMapping("/utenti")
    public ResponseEntity<Utente> insertUtente(@RequestBody Utente u){
        return ResponseEntity.ok().body(utenteService.saveUtente(u));
    }

    //modifico un utente
    @PatchMapping("/utenti/{id}")
    public ResponseEntity updateUtenteById(@PathVariable Long id , @RequestBody Utente u){
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
