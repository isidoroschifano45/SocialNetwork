package org.elis.socialnetwork.mapper.utente;

import org.elis.socialnetwork.dto.request.utente.UtenteRegisterDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteResponseDTO;
import org.elis.socialnetwork.dto.response.utente.UtenteWithFollowDTO;
import org.elis.socialnetwork.model.Utente;
import org.springframework.stereotype.Component;

@Component
public class UtenteMapper {

    public Utente formInsertUtente(UtenteRegisterDTO utenteDTO){
        Utente u = new Utente();
        u.setUsername(utenteDTO.getUsername());
        u.setEmail(utenteDTO.getEmail());
        u.setPassword(utenteDTO.getPassword());

        return u;
    }

    public UtenteResponseDTO convertToDTO(Utente u){
        UtenteResponseDTO utenteResponse = new UtenteResponseDTO();
        utenteResponse.setId(u.getId());
        utenteResponse.setUsername(u.getUsername());
        utenteResponse.setEmail(u.getEmail());
        utenteResponse.setFollowers(u.getFollowers() == null ? 0 : u.getFollowers().size());
        utenteResponse.setFollowing(u.getFollowing() == null ? 0 : u.getFollowing().size());
        utenteResponse.setRuolo(u.getRuolo());
        return utenteResponse;
    }

    public UtenteWithFollowDTO convertToDTOWithFollow(Utente u){
        UtenteWithFollowDTO utenteWithFollow = new UtenteWithFollowDTO();
        utenteWithFollow.setId(u.getId());
        utenteWithFollow.setUsername(u.getUsername());
        utenteWithFollow.setFollowers(u.getFollowers().stream()
                .map(this::convertToDTO)
                .toList());
        utenteWithFollow.setFollowing(u.getFollowing().stream()
                .map(this::convertToDTO)
                .toList());

        return  utenteWithFollow;
    }


}
