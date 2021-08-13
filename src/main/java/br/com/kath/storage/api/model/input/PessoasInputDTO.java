package br.com.kath.storage.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PessoasInputDTO {

    @NotBlank
    @Size(max = 150)
    private String nome;

    @NotBlank
    @Size(max = 200)
    private String email;

    @NotBlank
    @Size(max = 200)
    private String senha;

}
