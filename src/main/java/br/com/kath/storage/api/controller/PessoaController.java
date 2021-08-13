package br.com.kath.storage.api.controller;

import br.com.kath.storage.api.assembler.PessoasAssembler;
import br.com.kath.storage.api.model.PessoasDTO;
import br.com.kath.storage.api.model.input.PessoasInputDTO;
import br.com.kath.storage.domain.model.Pessoas;
import br.com.kath.storage.domain.repository.PessoasRepository;
import br.com.kath.storage.domain.services.PessoasPerfisService;
import br.com.kath.storage.domain.services.PessoasService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoasService pessoasService;
    private PessoasPerfisService pessoasPerfisService;

    private PessoasAssembler pessoasAssembler;

    private PessoasRepository pessoasRepository;

    @PostMapping
    public PessoasDTO cadastrar(@Valid @RequestBody PessoasInputDTO pessoasInputDTO) {
        Pessoas novaPessoa = pessoasAssembler.toEntity(pessoasInputDTO);

        novaPessoa.setSenha(new BCryptPasswordEncoder().encode(novaPessoa.getSenha()));

        Pessoas pessoas = pessoasService.cadastrar(novaPessoa);

        pessoasPerfisService.cadastrar(pessoas.getCodigo());

        return pessoasAssembler.toModel(pessoas);
    }

    @GetMapping
    public List<PessoasDTO> listar() {
        return pessoasService.listar();
    }

    @GetMapping("/{codigoPessoa}")
    public ResponseEntity<PessoasDTO> buscar(@PathVariable long codigoPessoa) {
        return pessoasService.buscar(codigoPessoa);
    }

    @PutMapping("/{codigoPessoa}")
    public ResponseEntity<PessoasDTO> editar(
            @RequestBody PessoasInputDTO pessoasInputDTO,
            @PathVariable long codigoPessoa
    ) {
        if (!pessoasRepository.existsById(codigoPessoa)) {
            return ResponseEntity.notFound().build();
        }

        Pessoas novaPessoa = pessoasAssembler.toEntity(pessoasInputDTO);

        novaPessoa.setSenha(new BCryptPasswordEncoder().encode(novaPessoa.getSenha()));

        Pessoas pessoas = pessoasService.editar(novaPessoa, codigoPessoa);

        return ResponseEntity.ok(pessoasAssembler.toModel(pessoas));
    }

    @DeleteMapping("/{codigoPessoa}")
    public ResponseEntity<Pessoas> remover(@PathVariable long codigoPessoa) {
        if (!pessoasRepository.existsById(codigoPessoa)) {
            return ResponseEntity.notFound().build();
        }

        pessoasPerfisService.remover(codigoPessoa);

        pessoasService.remover(codigoPessoa);

        return ResponseEntity.noContent().build();
    }

}
