package com.cemi.papelaria.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.cemi.papelaria.domain.Usuario;
import com.cemi.papelaria.dto.request.LoginRequest;
import com.cemi.papelaria.dto.response.UsuarioResponse;
import com.cemi.papelaria.repository.UsuarioRepository;

@SpringBootTest
@Transactional
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();

        usuario = new Usuario();
        usuario.setNome("TstUser");
        usuario.setEmail("tstuser@teste.com");
        usuario.setSenha("tstpwd12");
        usuario = usuarioRepository.save(usuario);
    }

    @Test
    void login_Sucesso() {
        LoginRequest request = new LoginRequest();
        request.setEmail("tstuser@teste.com");
        request.setSenha("tstpwd12");

        UsuarioResponse response = usuarioService.login(request);

        assertNotNull(response);
        assertEquals(usuario.getIdUsuario(), response.getIdUsuario());
        assertEquals("TstUser", response.getNome());
        assertEquals("tstuser@teste.com", response.getEmail());
    }

    @Test
    void login_EmailNaoEncontrado() {
        LoginRequest request = new LoginRequest();
        request.setEmail("outro@teste.com");
        request.setSenha("tstpwd12");

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            usuarioService.login(request);
        });

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        assertEquals("E-mail ou senha incorretos!", exception.getReason());
    }

    @Test
    void login_SenhaIncorreta() {
        LoginRequest request = new LoginRequest();
        request.setEmail("tstuser@teste.com");
        request.setSenha("errada");

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            usuarioService.login(request);
        });

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        assertEquals("E-mail ou senha incorretos!", exception.getReason());
    }
}
