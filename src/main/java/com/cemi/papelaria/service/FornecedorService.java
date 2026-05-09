package com.cemi.papelaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cemi.papelaria.domain.Fornecedor;
import com.cemi.papelaria.repository.FornecedorRepository;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public Fornecedor adicionar(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}
	
	public Fornecedor buscarPorId(Long id) {
		Optional<Fornecedor> obj = fornecedorRepository.findById(id);
		return obj.orElseThrow(() -> new RuntimeException("Fornecedor não encontrado! Id: " + id ));
	}
	
	public List<Fornecedor> buscarTodos(){
		return fornecedorRepository.findAll();
	}
	
	public Fornecedor alterar(Long id, Fornecedor objNovo) {
		Fornecedor objAntigo = buscarPorId(id);
		atualizarDados(objAntigo, objNovo);
		return fornecedorRepository.save(objAntigo);
	}
	
	private void atualizarDados(Fornecedor objAntigo, Fornecedor objNovo) {
		objAntigo.setNomeFornecedor(objNovo.getNomeFornecedor());
		objAntigo.setCnpjFornecedor(objNovo.getCnpjFornecedor());
		objAntigo.setTelefoneFornecedor(objNovo.getTelefoneFornecedor());
		objAntigo.setEmailFornecedor(objNovo.getEmailFornecedor());
		objAntigo.setProdutos(objNovo.getProdutos());
	}
	
	public void excluir(Long id) {
		buscarPorId(id);
		fornecedorRepository.deleteById(id);
	}

}
