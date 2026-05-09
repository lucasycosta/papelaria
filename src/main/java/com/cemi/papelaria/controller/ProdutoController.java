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

import com.cemi.papelaria.domain.Produto;
import com.cemi.papelaria.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Produto> adicionar(@Valid @RequestBody Produto produto) {
		Produto obj = produtoService.adicionar(produto);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
		Produto obj = produtoService.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<Produto>> buscarTodos() {
		List<Produto> list = produtoService.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> alterar(@PathVariable Long id, @Valid @RequestBody Produto objNovo) {
		Produto obj = produtoService.alterar(id, objNovo);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		produtoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
