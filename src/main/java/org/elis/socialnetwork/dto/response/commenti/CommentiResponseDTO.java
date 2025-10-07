package org.elis.socialnetwork.dto.response.commenti;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentiResponseDTO {

    private Long idCommento;
    private String username;
    private String testo;

}
