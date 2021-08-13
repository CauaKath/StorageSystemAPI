package br.com.kath.storage.domain.services;

import br.com.kath.storage.domain.model.PessoasPerfis;
import br.com.kath.storage.domain.repository.PessoasPerfisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PessoasPerfisService {

    private PessoasPerfisRepository pessoasPerfisRepository;

    @Transactional
    public void cadastrar(long pessoaId) {
        PessoasPerfis pessoasPerfis = new PessoasPerfis();
        pessoasPerfis.setPessoas_codigo(pessoaId);
        pessoasPerfis.setPerfis_nome("ROLE_USER");

        pessoasPerfisRepository.save(pessoasPerfis);
    }

    public void remover(long codigoPessoa) {
        pessoasPerfisRepository.delete(pessoasPerfisRepository.findByPessoasCodigo(codigoPessoa));
    }

}
