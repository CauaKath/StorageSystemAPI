package br.com.kath.storage.domain.repository;

import br.com.kath.storage.domain.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Long> {

    @Query("SELECT p FROM Pessoas p WHERE p.email = ?1")
    Pessoas findByEmail(String email);

}
