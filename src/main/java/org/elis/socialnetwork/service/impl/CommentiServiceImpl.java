package org.elis.socialnetwork.service.impl;

import lombok.RequiredArgsConstructor;
import org.elis.socialnetwork.dto.request.commenti.CommentoCreateDTO;
import org.elis.socialnetwork.dto.request.commenti.CommentoUpdateDTO;
import org.elis.socialnetwork.exception.post.PostNotAllowed;
import org.elis.socialnetwork.exception.post.PostNotFoundException;
import org.elis.socialnetwork.exception.utente.UtenteNotFoundException;
import org.elis.socialnetwork.model.Commenti;
import org.elis.socialnetwork.model.Post;
import org.elis.socialnetwork.model.Utente;
import org.elis.socialnetwork.repository.CommentiRepository;
import org.elis.socialnetwork.repository.PostRepository;
import org.elis.socialnetwork.repository.UtenteRepository;
import org.elis.socialnetwork.service.CommentiService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentiServiceImpl implements CommentiService {

    private final CommentiRepository commentiRepo;
    private final UtenteRepository utenteRepo;
    private final PostRepository postRepo;

    @Override
    public Commenti creaCommento(CommentoCreateDTO commento , String username, Long idPost) {
        Utente u = utenteRepo.findUtenteByUsername(username).orElseThrow(() -> new UtenteNotFoundException("Utente con username: " + username + " non trovato"));
        Post postCommentato = postRepo.findById(idPost).orElseThrow(()->new PostNotFoundException("Post con id: "+idPost+" non trovato"));
        Commenti commentoCreato = new Commenti();
        commentoCreato.setTesto(commento.getTestoCommento());
        commentoCreato.setDataEOra(LocalDateTime.now());
        commentoCreato.setPost(postCommentato);
        commentoCreato.setUtente(u);

        return commentiRepo.save(commentoCreato);

    }

    @Override
    public void deleteCommento(Long idCommento, String username , Long idPost) {

        Utente u = utenteRepo.findUtenteByUsername(username).orElseThrow(() -> new UtenteNotFoundException("Utente con username: " + username + " non trovato"));
        Post postCommentato = postRepo.findById(idPost).orElseThrow(()->new PostNotFoundException("Post con id: "+idPost+" non trovato"));
        if(!postCommentato.getUtente().getUsername().equals(u.getUsername())){
            throw new PostNotAllowed("Non sei il proprietario di commento");
        }
        commentiRepo.deleteById(idCommento);

    }

   @Override
   public void updateCommento(CommentoUpdateDTO commento, String username, Long idPost , Long idCommento) {
       Utente u = utenteRepo.findUtenteByUsername(username)
               .orElseThrow(() -> new UtenteNotFoundException("Utente con username: " + username + " non trovato"));
       Post postCommentato = postRepo.findById(idPost)
               .orElseThrow(() -> new PostNotFoundException("Post con id: " + idPost + " non trovato"));

       Commenti commentoEsistente = commentiRepo.findById(idCommento)
               .orElseThrow(() -> new PostNotFoundException("Commento con id: " + idCommento + " non trovato"));

       if (!commentoEsistente.getUtente().getUsername().equals(u.getUsername())) {
           throw new PostNotAllowed("Non sei il proprietario del commento");
       }
       if (!commentoEsistente.getPost().getId().equals(idPost)) {
           throw new PostNotAllowed("Il commento non appartiene a questo post");
       }

       commentoEsistente.setTesto(commento.getTestoCommento());
       commentiRepo.save(commentoEsistente);
   }
}
