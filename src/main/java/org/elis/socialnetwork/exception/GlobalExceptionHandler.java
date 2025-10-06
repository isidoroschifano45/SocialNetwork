package org.elis.socialnetwork.exception;

import org.elis.socialnetwork.exception.post.PostNotAllowed;
import org.elis.socialnetwork.exception.post.PostNotFoundException;
import org.elis.socialnetwork.exception.utente.UtenteAlreadyFollowed;
import org.elis.socialnetwork.exception.utente.UtenteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleSQLError(SQLIntegrityConstraintViolationException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");
        body.put("message", ex.getMessage());
        body.put("descrizione", "Guarda che stai cercando di salvare a DB qualche valore che va in conflitto, probabile qualcosa di unique");
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UtenteNotFoundException.class)
    public ResponseEntity<?> handleUtenteNotFound(UtenteNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("descrizione", "L'utente che stai cercando non esiste nel DB");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
            errors.put(error.getField() + "_valore", String.valueOf(error.getRejectedValue()));
        }
        body.put("message", "Errore di validazione");
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UtenteAlreadyFollowed.class)
    public ResponseEntity<?> handleUtenteNotFound(UtenteAlreadyFollowed ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_ACCEPTABLE.value());
        body.put("error", "Not Acceptable");
        body.put("message", ex.getMessage());
        body.put("descrizione", "Segui già questo utente");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class)
        public ResponseEntity<?> handlePostNotFound(PostNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", "Not Found");
            body.put("message", ex.getMessage());
            body.put("descrizione", "Il post che stai cercando non esiste nel DB");
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

    @ExceptionHandler(PostNotAllowed.class)
    public ResponseEntity<?> handlePostNotAllowed(PostNotAllowed ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_ACCEPTABLE.value());
        body.put("error", "Not Acceptable");
        body.put("message", ex.getMessage());
        body.put("descrizione", "Il post che stai cercando di modificare non e' tuo");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    }

