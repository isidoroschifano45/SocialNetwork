package org.elis.socialnetwork.dto.response.utente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UtenteWithFollowDTO {

    private Long id;
    private String username;
    private List<UtenteResponseDTO> followers;
    private List<UtenteResponseDTO> following;

}
