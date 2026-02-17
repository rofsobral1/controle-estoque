package com.gestao.estoque_backend.repository;

import com.gestao.estoque_backend.model.LogIa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogIaRepository extends JpaRepository<LogIa, Long> {
    List<LogIa> findByDescricaoContainingIgnoreCase(String descricao);
}