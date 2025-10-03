package org.elis.socialnetwork.dto.response.utente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UtenteResponseDTO {

    private Long id;
    private String username;
    private String email;

}
