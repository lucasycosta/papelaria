package com.cemi.papelaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.cemi.papelaria.domain.Cliente;
import com.cemi.papelaria.repository.ClienteRepository;

@SpringBootTest
@Transactional
public class ClienteTest {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	private Cliente cliente;
	
	@BeforeEach
	void setUp() {
		cliente = new Cliente();
		cliente.setNome("Lucas");
		cliente.setCpf("01223595644");
		cliente.setTelefone("61999999999");
		cliente.setEmail("teste@mail.com");
	}  
	/*
	
	@Test
	@Rollback(false)
	void cadastrarCliente() {
		Cliente resultado = clienteService.adicionar(cliente);

        // Assert
        assertNotNull(resultado.getIdCliente());
        
        // Busca do banco para confirmar a persistência
        Cliente clienteNoBanco = clienteRepository.findById(resultado.getIdCliente()).orElse(null);
        
        assertNotNull(clienteNoBanco);
        assertEquals("Lucas", clienteNoBanco.getNome());
        assertEquals("01223595644", clienteNoBanco.getCpf());
	}*/
	
}
