package com.cemi.papelaria.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cemi.papelaria.domain.Venda;
import com.cemi.papelaria.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	/**
	 * Adiciona uma nova venda.
	 */
	public Venda adicionar(Venda venda) {
		if (venda.getDataVenda() == null) {
			venda.setDataVenda(new Date());
		}
		return vendaRepository.save(venda);
	}

	/**
	 * Busca uma venda pelo ID.
	 */
	public Venda buscarPorId(Long id) {
		Optional<Venda> obj = vendaRepository.findById(id);
		return obj.orElseThrow(() -> new RuntimeException("Venda não encontrada! Id: " + id));
	}

	/**
	 * Busca todas as vendas.
	 */
	public List<Venda> buscarTodos() {
		return vendaRepository.findAll();
	}

	/**
	 * Altera os dados de uma venda existente.
	 */
	public Venda alterar(Long id, Venda objNovo) {
		Venda objAntigo = buscarPorId(id);
		atualizarDados(objAntigo, objNovo);
		return vendaRepository.save(objAntigo);
	}

	/**
	 * Exclui uma venda pelo ID.
	 */
	public void excluir(Long id) {
		buscarPorId(id); // Verifica se existe
		vendaRepository.deleteById(id);
	}

	/**
	 * Método auxiliar para atualizar os campos do objeto antigo com os novos dados.
	 */
	private void atualizarDados(Venda objAntigo, Venda objNovo) {
		objAntigo.setDataVenda(objNovo.getDataVenda());
		objAntigo.setTotalVenda(objNovo.getTotalVenda());
		objAntigo.setFormaPagamento(objNovo.getFormaPagamento());
		objAntigo.setCliente(objNovo.getCliente());
		// Itens de venda geralmente são gerenciados de forma mais criteriosa, 
		// mas para o CRUD básico seguimos o padrão:
		objAntigo.setItens(objNovo.getItens());
	}
}
