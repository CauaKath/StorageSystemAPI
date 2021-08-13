package br.com.kath.storage.api.model;

import br.com.kath.storage.domain.model.Perfis;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PessoasDTO {

    private long codigo;

    private String nome;

    private String email;

    private String senha;

}
