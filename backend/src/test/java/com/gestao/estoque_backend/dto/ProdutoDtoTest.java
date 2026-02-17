package com.gestao.estoque_backend.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoDTOTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private ProdutoDTO criarProdutoValido() {
        return ProdutoDTO.builder()
                .id(1L)
                .nome("Notebook Gamer")
                .descricao("Notebook de alto desempenho")
                .preco(BigDecimal.valueOf(4500.00))
                .quantidade(10)
                .categoria("Informática")
                .build();
    }

    @Test
    void deveValidarProdutoValido() {
        ProdutoDTO produto = criarProdutoValido();

        Set<ConstraintViolation<ProdutoDTO>> violacoes = validator.validate(produto);

        assertTrue(violacoes.isEmpty(), "Não deveria haver violações para produto válido");
    }

    @Test
    void deveValidarNomeObrigatorio() {
        ProdutoDTO produto = criarProdutoValido();
        produto.setNome(null);

        Set<ConstraintViolation<ProdutoDTO>> violacoes = validator.validate(produto);

        assertFalse(violacoes.isEmpty());
        assertTrue(violacoes.stream().anyMatch(v -> v.getMessage().contains("obrigatório")));
    }

    @Test
    void deveValidarPrecoPositivo() {
        ProdutoDTO produto = criarProdutoValido();
        produto.setPreco(BigDecimal.ZERO);

        Set<ConstraintViolation<ProdutoDTO>> violacoes = validator.validate(produto);

        assertFalse(violacoes.isEmpty());
        assertTrue(violacoes.stream().anyMatch(v -> v.getMessage().contains("maior que zero")));
    }

    @Test
    void deveValidarQuantidadeNaoNegativa() {
        ProdutoDTO produto = criarProdutoValido();
        produto.setQuantidade(-1);

        Set<ConstraintViolation<ProdutoDTO>> violacoes = validator.validate(produto);

        assertFalse(violacoes.isEmpty());
        assertTrue(violacoes.stream().anyMatch(v -> v.getMessage().contains("não pode ser negativa")));
    }

    @Test
    void deveValidarTamanhoMaximoNome() {
        ProdutoDTO produto = criarProdutoValido();
        produto.setNome("A".repeat(101)); // 101 caracteres

        Set<ConstraintViolation<ProdutoDTO>> violacoes = validator.validate(produto);

        assertFalse(violacoes.isEmpty());
        assertTrue(violacoes.stream().anyMatch(v -> v.getMessage().contains("size must be between")));
    }

    @Test
    void deveValidarTamanhoMaximoCategoria() {
        ProdutoDTO produto = criarProdutoValido();
        produto.setCategoria("ELETRONICOS"); // 51 caracteres

        Set<ConstraintViolation<ProdutoDTO>> violacoes = validator.validate(produto);

        assertFalse(violacoes.isEmpty());
        assertTrue(violacoes.stream().anyMatch(v -> v.getMessage().contains("size must be between")));
    }
}
