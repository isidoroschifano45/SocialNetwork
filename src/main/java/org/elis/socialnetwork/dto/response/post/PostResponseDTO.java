package org.elis.socialnetwork.dto.response.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;
import org.elis.socialnetwork.model.Utente;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDTO {

    private Long id;
    private String testo;
    private LocalDateTime dataEOra;
    private Integer likes;

}
