package org.elis.socialnetwork.dto.request.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateDTO {

    private String testo;
    private LocalDateTime dataEOra;


}
