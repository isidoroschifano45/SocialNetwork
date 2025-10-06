package org.elis.socialnetwork.dto.response.utente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elis.socialnetwork.model.Ruolo;

@Getter
@Setter
@NoArgsConstructor
public class UtenteResponseDTO {

    private Long id;
    private String username;
    private String email;
    private Integer followers;
    private Integer following;
    private Ruolo ruolo;

}
