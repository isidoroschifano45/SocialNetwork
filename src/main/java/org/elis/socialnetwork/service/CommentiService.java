package org.elis.socialnetwork.service;

import org.elis.socialnetwork.dto.request.commenti.CommentoCreateDTO;
import org.elis.socialnetwork.dto.request.commenti.CommentoUpdateDTO;
import org.elis.socialnetwork.model.Commenti;

public interface CommentiService {

    Commenti creaCommento(CommentoCreateDTO commento , String username , Long idPost);
    void deleteCommento(Long idCommento , String username , Long idPost);
    void updateCommento(CommentoUpdateDTO commento, String username , Long idPost , Long idCommento);

}
