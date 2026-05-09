package com.cemi.papelaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cemi.papelaria.domain.Cliente;
import com.cemi.papelaria.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<Cliente> adicionar(@Valid @RequestBody Cliente cliente) {
		Cliente obj = clienteService.adicionar(cliente);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		Cliente obj = clienteService.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> buscarTodos() {
		List<Cliente> list = clienteService.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> alterar(@PathVariable Long id, @Valid @RequestBody Cliente objNovo) {
		Cliente obj = clienteService.alterar(id, objNovo);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		clienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
