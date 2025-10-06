package org.elis.socialnetwork.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.utente.LoginDTO;
import org.elis.socialnetwork.dto.request.utente.UtenteRegisterDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;
import org.elis.socialnetwork.mapper.utente.UtenteMapper;
import org.elis.socialnetwork.model.Utente;
import org.elis.socialnetwork.security.config.JwtUtilities;
import org.elis.socialnetwork.service.UtenteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Controller dell' AUTENTICAZIONE", description = "API per login e Registrazione")
public class AuthController {
    private final JwtUtilities jwtUtilities;
    private final UtenteService utenteService;
    private final UtenteMapper utenteMapper;

    @Operation(summary = "Esegui il login")
    @PostMapping("/login")
    public ResponseEntity<UtenteResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO){
        Utente login = utenteService.login(loginDTO);
        String token = jwtUtilities.generaToken(login);

        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.AUTHORIZATION, token).body(utenteMapper.convertToDTO(login));
    }

    @Operation(summary = "Registra un utente")
    @PostMapping("/registrazione")
    public ResponseEntity<UtenteResponseDTO> registraUtente(@Valid @RequestBody UtenteRegisterDTO u){
        Utente utenteSalvato = utenteService.registraUtente(utenteMapper.formInsertUtente(u));

        return ResponseEntity.ok().body(utenteMapper.convertToDTO(utenteSalvato));
    }

}
