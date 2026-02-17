package com.gestao.estoque_backend.repository;

import com.gestao.estoque_backend.model.PrevisaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrevisaoEstoqueRepository extends JpaRepository<PrevisaoEstoque, Long> {

    // Busca todas as previsões de estoque de um produto específico
    List<PrevisaoEstoque> findByProdutoId(Long produtoId);
}