package org.elis.socialnetwork.mapper;

import org.elis.socialnetwork.dto.response.commenti.CommentiResponseDTO;
import org.elis.socialnetwork.model.Commenti;
import org.springframework.stereotype.Component;

@Component
public class CommentiMapper {

    public CommentiResponseDTO convertToDTO(Commenti commento) {
        CommentiResponseDTO dto = new CommentiResponseDTO();
        dto.setIdCommento(commento.getId());
        dto.setTesto(commento.getTesto());
        dto.setUsername(commento.getUtente().getUsername());
        return dto;
    }

}
