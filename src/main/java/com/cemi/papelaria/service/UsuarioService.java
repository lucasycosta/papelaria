package com.cemi.papelaria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cemi.papelaria.domain.Usuario;
import com.cemi.papelaria.dto.request.LoginRequest;
import com.cemi.papelaria.dto.request.UsuarioRequest;
import com.cemi.papelaria.dto.response.UsuarioResponse;
import com.cemi.papelaria.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Realiza o login do usuário.
	 */
	public UsuarioResponse login(LoginRequest request) {
		Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha incorretos!"));

		if (!usuario.getSenha().equals(request.getSenha())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha incorretos!");
		}

		return toResponse(usuario);
	}

	/**
	 * Adiciona um novo usuário.
	 */
	public UsuarioResponse adicionar(UsuarioRequest request) {
		Usuario usuario = toEntity(request);
		usuario = usuarioRepository.save(usuario);
		return toResponse(usuario);
	}

	/**
	 * Busca um usuário pelo ID.
	 */
	public UsuarioResponse buscarPorId(Long id) {
		Usuario usuario = findEntityById(id);
		return toResponse(usuario);
	}

	/**
	 * Busca todos os usuários.
	 */
	public List<UsuarioResponse> buscarTodos() {
		return usuarioRepository.findAll().stream()
				.map(this::toResponse)
				.collect(Collectors.toList());
	}

	/**
	 * Altera os dados de um usuário existente.
	 */
	public UsuarioResponse alterar(Long id, UsuarioRequest request) {
		Usuario usuario = findEntityById(id);
		atualizarDados(usuario, request);
		usuario = usuarioRepository.save(usuario);
		return toResponse(usuario);
	}

	/**
	 * Exclui um usuário pelo ID.
	 */
	public void excluir(Long id) {
		findEntityById(id); // Verifica se existe
		usuarioRepository.deleteById(id);
	}

	/**
	 * Método auxiliar para atualizar os campos do objeto antigo com os novos dados.
	 */
	private void atualizarDados(Usuario usuario, UsuarioRequest request) {
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setSenha(request.getSenha());
	}

	private Usuario toEntity(UsuarioRequest request) {
		Usuario usuario = new Usuario();
		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setSenha(request.getSenha());
		return usuario;
	}

	private UsuarioResponse toResponse(Usuario usuario) {
		UsuarioResponse response = new UsuarioResponse();
		response.setIdUsuario(usuario.getIdUsuario());
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());
		response.setSenha(usuario.getSenha());
		return response;
	}

	private Usuario findEntityById(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado! Id: " + id));
	}
}
