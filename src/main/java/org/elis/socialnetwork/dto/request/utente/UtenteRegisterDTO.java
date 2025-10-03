package org.elis.socialnetwork.dto.request.utente;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UtenteRegisterDTO {
    private String username;
    private String email;
    private String password;

}
