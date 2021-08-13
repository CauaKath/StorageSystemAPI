package br.com.kath.storage.domain.model;

import br.com.kath.storage.api.model.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {

    private String jwt;

}
