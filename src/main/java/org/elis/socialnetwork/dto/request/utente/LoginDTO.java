package org.elis.socialnetwork.dto.request.utente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {

    private String email;
    private String password;
}
