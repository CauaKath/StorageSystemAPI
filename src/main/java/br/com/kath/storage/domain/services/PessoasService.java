package br.com.kath.storage.domain.services;

import br.com.kath.storage.api.assembler.PessoasAssembler;
import br.com.kath.storage.api.model.PessoasDTO;
import br.com.kath.storage.domain.model.Pessoas;
import br.com.kath.storage.domain.repository.PessoasRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@AllArgsConstructor
@Service
public class PessoasService {

    private PessoasRepository pessoasRepository;

    private PessoasAssembler pessoasAssembler;

    @Transactional
    public Pessoas cadastrar(Pessoas pessoas) {
        return pessoasRepository.save(pessoas);
    }

    public List<PessoasDTO> listar() {
        return pessoasAssembler.toCollectionModel(pessoasRepository.findAll());
    }

    public ResponseEntity<PessoasDTO> buscar(long codigoPessoa) {
        return pessoasRepository.findById(codigoPessoa)
                .map(pessoas -> ResponseEntity.ok(pessoasAssembler.toModel(pessoas)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public Pessoas editar(Pessoas pessoas, long codigoPessoa) {
        pessoas.setCodigo(codigoPessoa);

        return pessoasRepository.save(pessoas);
    }

    public void remover(long codigoPessoa) {
        pessoasRepository.deleteById(codigoPessoa);
    }

}
