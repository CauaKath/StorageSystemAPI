package br.com.kath.storage.domain.services;

import br.com.kath.storage.api.assembler.ProdutosAssembler;
import br.com.kath.storage.api.model.ProdutosDTO;
import br.com.kath.storage.domain.exception.EntityAlreadyExistsException;
import br.com.kath.storage.domain.model.Produtos;
import br.com.kath.storage.domain.repository.ProdutosRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutosService {

    private ProdutosRepository produtosRepository;

    private ProdutosAssembler produtosAssembler;

    @Transactional
    public Produtos cadastrar(Produtos produtos) throws Exception {
        if (produtosRepository.findByProduto(produtos.getProduto()) != null) {
            throw new EntityAlreadyExistsException("Produto já cadastrado!");
        }

        produtos.setValorTotalEmEstoque(calcValorTotal(produtos));

        return produtosRepository.save(produtos);
    }

    public List<ProdutosDTO> listar() {
        return produtosAssembler.toCollectionModel(produtosRepository.findAll());
    }

    public ResponseEntity<ProdutosDTO> buscar(long codigoProduto) {
        return produtosRepository.findById(codigoProduto)
                .map(produtos -> ResponseEntity.ok(produtosAssembler.toModel(produtos)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public Produtos editar(Produtos produtos, long codigoProduto) throws Exception {
        if (produtos.getProduto() == null) {
            produtos.setProduto(produtosRepository.getById(codigoProduto).getProduto());
        } else {
            if (produtosRepository.findByProduto(produtos.getProduto()) != null) {
                throw new EntityAlreadyExistsException("Produto já cadastrado!");
            }
        }

        produtos.setCodigo(codigoProduto);
        produtos.setValorTotalEmEstoque(calcValorTotal(produtos));

        return produtosRepository.save(produtos);
    }

    public void remover(long codigoProduto) {
        produtosRepository.deleteById(codigoProduto);
    }

    private double calcValorTotal(Produtos produtos) {
        return produtos.getValorUnitario() * produtos.getQuantidade();
    }

}
