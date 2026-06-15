package com.cemi.papelaria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cemi.papelaria.domain.Produto;
import com.cemi.papelaria.dto.request.ProdutoRequest;
import com.cemi.papelaria.dto.response.ProdutoResponse;
import com.cemi.papelaria.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	/**
	 * Adiciona um novo produto.
	 */
	public ProdutoResponse adicionar(ProdutoRequest request) {
		Produto produto = toEntity(request);
		produto = produtoRepository.save(produto);
		return toResponse(produto);
	}

	/**
	 * Busca um produto pelo ID.
	 */
	public ProdutoResponse buscarPorId(Long id) {
		Produto produto = findEntityById(id);
		return toResponse(produto);
	}

	/**
	 * Busca todos os produtos.
	 */
	public List<ProdutoResponse> buscarTodos() {
		return produtoRepository.findAll().stream()
				.map(this::toResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Altera os dados de um produto existente.
	 */
	public ProdutoResponse alterar(Long id, ProdutoRequest request) {
		Produto objAntigo = findEntityById(id);
		atualizarDados(objAntigo, request);
		objAntigo = produtoRepository.save(objAntigo);
		return toResponse(objAntigo);
	}

	/**
	 * Exclui um produto pelo ID.
	 */
	public void excluir(Long id) {
		findEntityById(id); // Verifica se existe
		produtoRepository.deleteById(id);
	}

	/**
	 * Método auxiliar para atualizar os campos do objeto antigo com os novos dados.
	 */
	private void atualizarDados(Produto objAntigo, ProdutoRequest request) {
		objAntigo.setNomeProduto(request.getNomeProduto());
		objAntigo.setDescricao(request.getDescricao());
		objAntigo.setCategoria(request.getCategoria());
		objAntigo.setPreco(request.getPreco());
		objAntigo.setEstoque(request.getEstoque());
		objAntigo.setDataValidade(request.getDataValidade());
	}

	private Produto toEntity(ProdutoRequest request) {
		Produto produto = new Produto();
		produto.setNomeProduto(request.getNomeProduto());
		produto.setDescricao(request.getDescricao());
		produto.setCategoria(request.getCategoria());
		produto.setPreco(request.getPreco());
		produto.setEstoque(request.getEstoque());
		produto.setDataValidade(request.getDataValidade());
		
		return produto;
	}

	private ProdutoResponse toResponse(Produto produto) {
		ProdutoResponse response = new ProdutoResponse();
		response.setIdProduto(produto.getIdProduto());
		response.setNomeProduto(produto.getNomeProduto());
		response.setDescricao(produto.getDescricao());
		response.setCategoria(produto.getCategoria());
		response.setPreco(produto.getPreco());
		response.setEstoque(produto.getEstoque());
		response.setDataValidade(produto.getDataValidade());
		
		return response;
	}

	private Produto findEntityById(Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado! Id: " + id));
	}
}
