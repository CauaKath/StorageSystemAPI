package br.com.kath.storage.domain.repository;

import br.com.kath.storage.domain.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    @Query("SELECT p FROM Produtos p WHERE p.produto = ?1")
    Produtos findByProduto(String produto);

}
