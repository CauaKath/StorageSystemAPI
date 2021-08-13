package br.com.kath.storage.api.controller;

import br.com.kath.storage.api.assembler.ProdutosAssembler;
import br.com.kath.storage.api.model.ProdutosDTO;
import br.com.kath.storage.api.model.input.ProdutosInputDTO;
import br.com.kath.storage.domain.model.Produtos;
import br.com.kath.storage.domain.repository.ProdutosRepository;
import br.com.kath.storage.domain.services.ProdutosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    private ProdutosService produtosService;

    private ProdutosAssembler produtosAssembler;

    private ProdutosRepository produtosRepository;

    @PostMapping
    public ResponseEntity<ProdutosDTO> cadastrar(@Valid @RequestBody ProdutosInputDTO produtosInputDTO) throws Exception {
        Produtos novoProduto = produtosAssembler.toEntity(produtosInputDTO);

        Produtos produtos = produtosService.cadastrar(novoProduto);

        return ResponseEntity.ok(produtosAssembler.toModel(produtos));
    }

    @GetMapping
    public List<ProdutosDTO> listar() {
        return produtosService.listar();
    }

    @GetMapping("/{codigoProduto}")
    public ResponseEntity<ProdutosDTO> buscar(@PathVariable long codigoProduto) {
        return produtosService.buscar(codigoProduto);
    }

    @PutMapping("/{codigoProduto}")
    public ResponseEntity<ProdutosDTO> editar(
        @RequestBody ProdutosInputDTO produtosInputDTO,
        @PathVariable long codigoProduto
    ) throws Exception {
        if (!produtosRepository.existsById(codigoProduto)) {
            return ResponseEntity.notFound().build();
        }

        Produtos novoProduto = produtosAssembler.toEntity(produtosInputDTO);

        Produtos produtos = produtosService.editar(novoProduto, codigoProduto);

        return ResponseEntity.ok(produtosAssembler.toModel(produtos));
    }

    @DeleteMapping("/{codigoProduto}")
    public ResponseEntity<Produtos> remover(@PathVariable long codigoProduto) {
        if (!produtosRepository.existsById(codigoProduto)) {
            return ResponseEntity.notFound().build();
        }

        produtosService.remover(codigoProduto);

        return ResponseEntity.noContent().build();
    }

}
