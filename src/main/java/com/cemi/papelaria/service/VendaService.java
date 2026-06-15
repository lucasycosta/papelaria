package com.cemi.papelaria.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cemi.papelaria.domain.Cliente;
import com.cemi.papelaria.domain.ItemVenda;
import com.cemi.papelaria.domain.Produto;
import com.cemi.papelaria.domain.Venda;
import com.cemi.papelaria.dto.request.ItemVendaRequest;
import com.cemi.papelaria.dto.request.VendaRequest;
import com.cemi.papelaria.dto.response.ItemVendaResponse;
import com.cemi.papelaria.dto.response.VendaResponse;
import com.cemi.papelaria.repository.ClienteRepository;
import com.cemi.papelaria.repository.ProdutoRepository;
import com.cemi.papelaria.repository.VendaRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	/**
	 * Adiciona uma nova venda e atualiza o estoque dos produtos.
	 */
	@Transactional
	public VendaResponse adicionar(VendaRequest request) {
		Venda venda = toEntity(request);
		
		double total = 0.0;
		
		for (ItemVenda item : venda.getItens()) {
			Produto produto = item.getProduto();
			
			// Verifica se há estoque disponível
			if (produto.getEstoque() < item.getQuantidade()) {
				throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNomeProduto() + 
						". Disponível: " + produto.getEstoque() + ", Solicitado: " + item.getQuantidade());
			}
			
			// Subtrai a quantidade vendida do estoque
			produto.setEstoque(produto.getEstoque() - item.getQuantidade());
			produtoRepository.save(produto);
			
			total += item.getPrecoUnitario().doubleValue() * item.getQuantidade();
		}
		
		venda.setValorTotal(total);
		venda = vendaRepository.saveAndFlush(venda);
		return toResponse(venda);
	}

	/**
	 * Busca uma venda pelo ID.
	 */
	public VendaResponse buscarPorId(Long id) {
		Venda venda = findEntityById(id);
		return toResponse(venda);
	}

	/**
	 * Busca todas as vendas.
	 */
	@Transactional
	public List<VendaResponse> buscarTodos() {
		return vendaRepository.findAll().stream()
				.map(this::toResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Altera os dados de uma venda existente.
	 */
	@Transactional
	public VendaResponse alterar(Long id, VendaRequest request) {
		Venda objAntigo = findEntityById(id);
		
		// Atualiza o cliente
		Cliente cliente = clienteRepository.findById(request.getIdCliente())
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + request.getIdCliente()));
		objAntigo.setCliente(cliente);
		objAntigo.setFormaPagamento(request.getFormaPagamento());
		
		// Atualiza os itens (limpa e adiciona novos para simplificação do CRUD)
		objAntigo.getItens().clear();
		for (ItemVendaRequest itemReq : request.getItens()) {
			ItemVenda item = toItemEntity(itemReq, objAntigo);
			objAntigo.getItens().add(item);
		}
		
		// Recalcula o total
		double total = objAntigo.getItens().stream()
				.mapToDouble(item -> item.getPrecoUnitario().doubleValue() * item.getQuantidade())
				.sum();
		objAntigo.setValorTotal(total);
		
		objAntigo = vendaRepository.save(objAntigo);
		return toResponse(objAntigo);
	}

	/**
	 * Exclui uma venda pelo ID.
	 */
	public void excluir(Long id) {
		findEntityById(id); // Verifica se existe
		vendaRepository.deleteById(id);
	}

	private Venda toEntity(VendaRequest request) {
		Venda venda = new Venda();
		
		Cliente cliente = clienteRepository.findById(request.getIdCliente())
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + request.getIdCliente()));
		venda.setCliente(cliente);
		venda.setFormaPagamento(request.getFormaPagamento());
		
		List<ItemVenda> itens = new ArrayList<>();
		for (ItemVendaRequest itemReq : request.getItens()) {
			itens.add(toItemEntity(itemReq, venda));
		}
		venda.setItens(itens);
		
		return venda;
	}

	private ItemVenda toItemEntity(ItemVendaRequest request, Venda venda) {
		Produto produto = produtoRepository.findById(request.getIdProduto())
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + request.getIdProduto()));
		
		ItemVenda item = new ItemVenda();
		item.setProduto(produto);
		item.setQuantidade(request.getQuantidade());
		item.setPrecoUnitario(BigDecimal.valueOf(produto.getPreco()));
		item.setVenda(venda);
		
		return item;
	}

	private VendaResponse toResponse(Venda venda) {
		VendaResponse response = new VendaResponse();
		response.setIdVenda(venda.getIdVenda());
		response.setDataVenda(venda.getDataVenda());
		response.setValorTotal(venda.getValorTotal());
		response.setFormaPagamento(venda.getFormaPagamento());
		response.setIdCliente(venda.getCliente().getIdCliente());
		response.setNomeCliente(venda.getCliente().getNome());
		
		response.setItens(venda.getItens().stream()
				.map(this::toItemResponse)
				.collect(Collectors.toList()));
		
		return response;
	}

	private ItemVendaResponse toItemResponse(ItemVenda item) {
		ItemVendaResponse response = new ItemVendaResponse();
		response.setIdItemVenda(item.getIdItemVenda());
		response.setQuantidade(item.getQuantidade());
		response.setPrecoUnitario(item.getPrecoUnitario());
		response.setIdProduto(item.getProduto().getIdProduto());
		response.setNomeProduto(item.getProduto().getNomeProduto());
		response.setIdVenda(item.getVenda().getIdVenda());
		return response;
	}

	private Venda findEntityById(Long id) {
		return vendaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Venda não encontrada! Id: " + id));
	}
}
