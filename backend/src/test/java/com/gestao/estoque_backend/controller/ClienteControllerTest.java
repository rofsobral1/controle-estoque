package com.gestao.estoque_backend.controller;

import com.gestao.estoque_backend.dto.ClienteDTO;
import com.gestao.estoque_backend.service.ClienteService;
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

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarCliente() {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(1L);
        dto.setNome("João da Silva");

        when(clienteService.salvarCliente(dto)).thenReturn(dto);

        ResponseEntity<ClienteDTO> response = clienteController.criarCliente(dto);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
        verify(clienteService, times(1)).salvarCliente(dto);
    }

    @Test
    void testListarClientes() {
        ClienteDTO dto1 = new ClienteDTO();
        dto1.setId(1L);
        dto1.setNome("Cliente 1");

        ClienteDTO dto2 = new ClienteDTO();
        dto2.setId(2L);
        dto2.setNome("Cliente 2");

        List<ClienteDTO> clientes = Arrays.asList(dto1, dto2);

        when(clienteService.listarClientes()).thenReturn(clientes);

        ResponseEntity<List<ClienteDTO>> response = clienteController.listarClientes();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(clientes, response.getBody());
        verify(clienteService, times(1)).listarClientes();
    }

    @Test
    void testBuscarCliente() {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(1L);
        dto.setNome("Cliente Teste");

        when(clienteService.buscarPorId(1L)).thenReturn(dto);

        ResponseEntity<ClienteDTO> response = clienteController.buscarCliente(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
        verify(clienteService, times(1)).buscarPorId(1L);
    }

    @Test
    void testAtualizarCliente() {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(1L);
        dto.setNome("Cliente Atualizado");

        when(clienteService.atualizarCliente(1L, dto)).thenReturn(dto);

        ResponseEntity<ClienteDTO> response = clienteController.atualizarCliente(1L, dto);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(dto, response.getBody());
        verify(clienteService, times(1)).atualizarCliente(1L, dto);
    }

    @Test
    void testDeletarCliente() {
        doNothing().when(clienteService).deletarCliente(1L);

        ResponseEntity<Void> response = clienteController.deletarCliente(1L);

        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(clienteService, times(1)).deletarCliente(1L);
    }
}
