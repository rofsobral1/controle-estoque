package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.ProdutoDTO;
import com.gestao.estoque_backend.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarProduto() {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(1L);
        dto.setNome("Produto Teste");

        when(produtoService.salvarProduto(dto)).thenReturn(dto);

        ResponseEntity<ProdutoDTO> response = produtoController.criarProduto(dto);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
        verify(produtoService, times(1)).salvarProduto(dto);
    }

    @Test
    void testListarProdutos() {
        ProdutoDTO dto1 = new ProdutoDTO();
        dto1.setId(1L);
        dto1.setNome("Produto 1");

        ProdutoDTO dto2 = new ProdutoDTO();
        dto2.setId(2L);
        dto2.setNome("Produto 2");

        List<ProdutoDTO> produtos = Arrays.asList(dto1, dto2);

        when(produtoService.listarProdutos()).thenReturn(produtos);

        ResponseEntity<List<ProdutoDTO>> response = produtoController.listarProdutos();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(produtos, response.getBody());
        verify(produtoService, times(1)).listarProdutos();
    }

    @Test
    void testBuscarProduto() {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(1L);
        dto.setNome("Produto Teste");

        when(produtoService.buscarPorId(1L)).thenReturn(dto);

        ResponseEntity<ProdutoDTO> response = produtoController.buscarProduto(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
        verify(produtoService, times(1)).buscarPorId(1L);
    }

    @Test
    void testDeletarProduto() {
        doNothing().when(produtoService).deletarProduto(1L);

        ResponseEntity<Void> response = produtoController.deletarProduto(1L);

        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(produtoService, times(1)).deletarProduto(1L);
    }
}