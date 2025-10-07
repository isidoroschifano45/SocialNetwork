package org.elis.socialnetwork.dto.response.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elis.socialnetwork.dto.response.commenti.CommentiResponseDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;
import org.elis.socialnetwork.model.Utente;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDTO {

    private Long idPost;
    private String testoPost;
    private LocalDateTime dataEOra;
    private Integer likes;
    private String link;
    private List<CommentiResponseDTO> commenti;

}
