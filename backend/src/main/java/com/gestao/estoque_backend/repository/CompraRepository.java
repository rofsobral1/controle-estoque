package com.gestao.estoque_backend.repository;

import com.gestao.estoque_backend.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
