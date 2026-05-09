package com.cemi.papelaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cemi.papelaria.domain.ItemVenda;
import com.cemi.papelaria.repository.ItemVendaRepository;

@Service
public class ItemVendaService {

	@Autowired
	private ItemVendaRepository itemVendaRepository;

	/**
	 * Adiciona um novo item à venda.
	 */
	public ItemVenda adicionar(ItemVenda itemVenda) {
		return itemVendaRepository.save(itemVenda);
	}

	/**
	 * Busca um item pelo ID.
	 */
	public ItemVenda buscarPorId(Long id) {
		Optional<ItemVenda> obj = itemVendaRepository.findById(id);
		return obj.orElseThrow(() -> new RuntimeException("Item de venda não encontrado! Id: " + id));
	}

	/**
	 * Busca todos os itens de venda.
	 */
	public List<ItemVenda> buscarTodos() {
		return itemVendaRepository.findAll();
	}

	/**
	 * Altera os dados de um item existente.
	 */
	public ItemVenda alterar(Long id, ItemVenda objNovo) {
		ItemVenda objAntigo = buscarPorId(id);
		atualizarDados(objAntigo, objNovo);
		return itemVendaRepository.save(objAntigo);
	}

	/**
	 * Exclui um item pelo ID.
	 */
	public void excluir(Long id) {
		buscarPorId(id); // Verifica se existe
		itemVendaRepository.deleteById(id);
	}

	/**
	 * Método auxiliar para atualizar os campos do objeto antigo com os novos dados.
	 * Por ser uma entidade associativa, a atualização de Produto ou Venda deve ser feita com cautela.
	 */
	private void atualizarDados(ItemVenda objAntigo, ItemVenda objNovo) {
		objAntigo.setQuantidade(objNovo.getQuantidade());
		objAntigo.setPrecoUnitario(objNovo.getPrecoUnitario());
		
		// Em entidades associativas, geralmente permitimos trocar o Produto ou a Venda 
		// associada se o contexto do negócio permitir a correção do item.
		objAntigo.setProduto(objNovo.getProduto());
		objAntigo.setVenda(objNovo.getVenda());
	}
}
