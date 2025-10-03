package org.elis.socialnetwork.dto.request.utente;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UtenteRegisterDTO {
    private String username;
    private String email;
    @Size(min = 5, message = "La password deve contenere almeno 5 caratteri")
    private String password;

}
