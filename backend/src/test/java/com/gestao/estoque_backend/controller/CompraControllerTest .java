package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.CompraDTO;
import com.gestao.estoque_backend.service.CompraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompraControllerTest {

    @Mock
    private CompraService compraService;

    @InjectMocks
    private CompraController compraController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarCompra() {
        CompraDTO dto = new CompraDTO();
        dto.setId(1L);
        dto.setObservacao("Compra Teste");

        when(compraService.salvar(dto)).thenReturn(dto);

        ResponseEntity<CompraDTO> response = compraController.criar(dto);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
        verify(compraService, times(1)).salvar(dto);
    }

    @Test
    void testListarCompras() {
        CompraDTO dto1 = new CompraDTO();
        dto1.setId(1L);
        dto1.setObservacao("Compra 1");

        CompraDTO dto2 = new CompraDTO();
        dto2.setId(2L);
        dto2.setObservacao("Compra 2");

        List<CompraDTO> compras = Arrays.asList(dto1, dto2);

        when(compraService.listarTodos()).thenReturn(compras);

        ResponseEntity<List<CompraDTO>> response = compraController.listar();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(compras, response.getBody());
        verify(compraService, times(1)).listarTodos();
    }

    @Test
    void testBuscarCompraPorId() {
        CompraDTO dto = new CompraDTO();
        dto.setId(1L);
        dto.setObservacao("Compra Teste");

        when(compraService.buscarPorId(1L)).thenReturn(dto);

        ResponseEntity<CompraDTO> response = compraController.buscarPorId(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
        verify(compraService, times(1)).buscarPorId(1L);
    }

    @Test
    void testAtualizarCompra() {
        CompraDTO dto = new CompraDTO();
        dto.setId(1L);
        dto.setObservacao("Compra Atualizada");

        when(compraService.atualizar(1L, dto)).thenReturn(dto);

        ResponseEntity<CompraDTO> response = compraController.atualizar(1L, dto);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
        verify(compraService, times(1)).atualizar(1L, dto);
    }

    @Test
    void testDeletarCompra() {
        doNothing().when(compraService).deletar(1L);

        ResponseEntity<Void> response = compraController.deletar(1L);

        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(compraService, times(1)).deletar(1L);
    }
}
