package com.cemi.papelaria.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cemi.papelaria.domain.ItemVenda;
import com.cemi.papelaria.domain.Produto;
import com.cemi.papelaria.domain.Venda;
import com.cemi.papelaria.dto.request.ItemVendaRequest;
import com.cemi.papelaria.dto.response.ItemVendaResponse;
import com.cemi.papelaria.repository.ItemVendaRepository;
import com.cemi.papelaria.repository.ProdutoRepository;
import com.cemi.papelaria.repository.VendaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemVendaService {

	@Autowired
	private ItemVendaRepository itemVendaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private VendaRepository vendaRepository;

	/**
	 * Busca um item pelo ID.
	 */
	public ItemVendaResponse buscarPorId(Long id) {
		ItemVenda item = findEntityById(id);
		return toResponse(item);
	}

	/**
	 * Busca todos os itens de venda.
	 */
	public List<ItemVendaResponse> buscarTodos() {
		return itemVendaRepository.findAll().stream()
				.map(this::toResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Exclui um item pelo ID.
	 */
	public void excluir(Long id) {
		findEntityById(id); // Verifica se existe
		itemVendaRepository.deleteById(id);
	}

	private ItemVendaResponse toResponse(ItemVenda item) {
		ItemVendaResponse response = new ItemVendaResponse();
		response.setIdItemVenda(item.getIdItemVenda());
		response.setQuantidade(item.getQuantidade());
		response.setPrecoUnitario(item.getPrecoUnitario());
		response.setIdProduto(item.getProduto().getIdProduto());
		response.setNomeProduto(item.getProduto().getNomeProduto());
		response.setIdVenda(item.getVenda().getIdVenda());
		return response;
	}

	private ItemVenda findEntityById(Long id) {
		return itemVendaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Item de venda não encontrado! Id: " + id));
	}
}
