package br.com.kath.storage.domain.repository;

import br.com.kath.storage.domain.model.PessoasPerfis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasPerfisRepository extends JpaRepository<PessoasPerfis, Long> {

    @Query("SELECT p FROM PessoasPerfis p WHERE p.pessoas_codigo = ?1")
    PessoasPerfis findByPessoasCodigo(long pessoaId);

}
