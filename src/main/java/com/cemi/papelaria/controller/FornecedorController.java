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

import com.cemi.papelaria.domain.Fornecedor;
import com.cemi.papelaria.service.FornecedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;

	@PostMapping
	public ResponseEntity<Fornecedor> adicionar(@Valid @RequestBody Fornecedor fornecedor) {
		Fornecedor obj = fornecedorService.adicionar(fornecedor);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
		Fornecedor obj = fornecedorService.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<Fornecedor>> buscarTodos() {
		List<Fornecedor> list = fornecedorService.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> alterar(@PathVariable Long id, @Valid @RequestBody Fornecedor objNovo) {
		Fornecedor obj = fornecedorService.alterar(id, objNovo);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		fornecedorService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
