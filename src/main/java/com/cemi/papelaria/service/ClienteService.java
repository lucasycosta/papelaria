package com.cemi.papelaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cemi.papelaria.domain.Cliente;
import com.cemi.papelaria.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Adiciona um novo cliente.
	 */
	public Cliente adicionar(Cliente cliente) {
		//cliente.setIdCliente(null); // Garante que será uma inserção
		return clienteRepository.save(cliente);
	}

	/**
	 * Busca um cliente pelo ID.
	 */
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new RuntimeException("Cliente não encontrado! Id: " + id));
	}

	/**
	 * Busca todos os clientes.
	 */
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	/**
	 * Altera os dados de um cliente existente.
	 */
	public Cliente alterar(Long id, Cliente objNovo) {
		Cliente objAntigo = buscarPorId(id);
		atualizarDados(objAntigo, objNovo);
		return clienteRepository.save(objAntigo);
	}

	/**
	 * Exclui um cliente pelo ID.
	 */
	public void excluir(Long id) {
		buscarPorId(id); // Verifica se existe
		clienteRepository.deleteById(id);
	}

	/**
	 * Método auxiliar para atualizar os campos do objeto antigo com os novos dados.
	 */
	private void atualizarDados(Cliente objAntigo, Cliente objNovo) {
		objAntigo.setNome(objNovo.getNome());
		objAntigo.setCpf(objNovo.getCpf());
		objAntigo.setTelefone(objNovo.getTelefone());
		objAntigo.setEmail(objNovo.getEmail());
	}
}
