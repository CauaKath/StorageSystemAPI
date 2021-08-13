package br.com.kath.storage.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProdutosInputDTO {

    @Valid
    @NotBlank
    @Size(max = 150)
    private String produto;

    @NotNull
    private int quantidade;

    @NotNull
    private double valorUnitario;

}
