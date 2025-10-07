package org.elis.socialnetwork.dto.request.post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;
import org.elis.socialnetwork.model.Hastag;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateDTO {

    private String testoPost;
    private LocalDateTime dataEOra;
    private List<String> hastags;
    @Schema(example = "https://images.unsplash.com/photo-1759588032622-1388cf9505ad?q=80&w=1172&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
    private String link;

}
