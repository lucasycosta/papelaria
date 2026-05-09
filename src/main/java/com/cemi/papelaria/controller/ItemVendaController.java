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

import com.cemi.papelaria.domain.ItemVenda;
import com.cemi.papelaria.service.ItemVendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/itens-venda")
public class ItemVendaController {

	@Autowired
	private ItemVendaService itemVendaService;

	@PostMapping
	public ResponseEntity<ItemVenda> adicionar(@Valid @RequestBody ItemVenda itemVenda) {
		ItemVenda obj = itemVendaService.adicionar(itemVenda);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemVenda> buscarPorId(@PathVariable Long id) {
		ItemVenda obj = itemVendaService.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<ItemVenda>> buscarTodos() {
		List<ItemVenda> list = itemVendaService.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemVenda> alterar(@PathVariable Long id, @Valid @RequestBody ItemVenda objNovo) {
		ItemVenda obj = itemVendaService.alterar(id, objNovo);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		itemVendaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
