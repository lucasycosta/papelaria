
package com.cemi.papelaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.cemi.papelaria.domain.Cliente;
import com.cemi.papelaria.repository.ClienteRepository;

@SpringBootTest
@Transactional
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setCpf("12345678901");
        cliente.setTelefone("11999999999");
        cliente.setEmail("joao@email.com");
    }

    @Test
    void adicionar_DevePersistirClienteNoBanco() {
        // Act
        Cliente resultado = clienteService.adicionar(cliente);

        // Assert
        assertNotNull(resultado.getIdCliente());
        
        // Busca do banco para confirmar a persistência
        Cliente clienteNoBanco = clienteRepository.findById(resultado.getIdCliente()).orElse(null);
        
        assertNotNull(clienteNoBanco);
        assertEquals("João Silva", clienteNoBanco.getNome());
        assertEquals("12345678901", clienteNoBanco.getCpf());
    }
}

