package br.com.kath.storage.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutosDTO {

    private long codigo;

    private String produto;

    private int quantidade;

    private double valorUnitario;

    private double valorTotalEmEstoque;

}
