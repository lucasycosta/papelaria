package com.cemi.papelaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cemi.papelaria.domain.Produto;
import com.cemi.papelaria.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	/**
	 * Adiciona um novo produto.
	 */
	public Produto adicionar(Produto produto) {
		return produtoRepository.save(produto);
	}

	/**
	 * Busca um produto pelo ID.
	 */
	public Produto buscarPorId(Long id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new RuntimeException("Produto não encontrado! Id: " + id));
	}

	/**
	 * Busca todos os produtos.
	 */
	public List<Produto> buscarTodos() {
		return produtoRepository.findAll();
	}

	/**
	 * Altera os dados de um produto existente.
	 */
	public Produto alterar(Long id, Produto objNovo) {
		Produto objAntigo = buscarPorId(id);
		atualizarDados(objAntigo, objNovo);
		return produtoRepository.save(objAntigo);
	}

	/**
	 * Exclui um produto pelo ID.
	 */
	public void excluir(Long id) {
		buscarPorId(id); // Verifica se existe
		produtoRepository.deleteById(id);
	}

	/**
	 * Método auxiliar para atualizar os campos do objeto antigo com os novos dados.
	 */
	private void atualizarDados(Produto objAntigo, Produto objNovo) {
		objAntigo.setNomeProduto(objNovo.getNomeProduto());
		objAntigo.setDescricao(objNovo.getDescricao());
		objAntigo.setCategoria(objNovo.getCategoria());
		objAntigo.setPreco(objNovo.getPreco());
		objAntigo.setEstoque(objNovo.getEstoque());
		objAntigo.setFornecedor(objNovo.getFornecedor());
	}
}
