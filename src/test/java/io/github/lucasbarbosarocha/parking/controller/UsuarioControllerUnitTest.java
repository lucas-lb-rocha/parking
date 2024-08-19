package io.github.lucasbarbosarocha.parking.controller;

import io.github.lucasbarbosarocha.parking.application.controller.UsuarioController;
import io.github.lucasbarbosarocha.parking.core.service.UsuarioService;
import io.github.lucasbarbosarocha.parking.domain.dto.UsuarioCreateDto;
import io.github.lucasbarbosarocha.parking.domain.dto.UsuarioResponseDto;
import io.github.lucasbarbosarocha.parking.domain.dto.UsuarioSenhaDto;
import io.github.lucasbarbosarocha.parking.domain.entity.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioControllerUnitTest {

    private final UsuarioService usuarioService = mock(UsuarioService.class);
    private final UsuarioController usuarioController = new UsuarioController(usuarioService);

    @Test
    @DisplayName("should return all users with status code ok")
    void shouldReturnAllUsersWithStatusCodeOk() {
        // GIVEN
        Usuario user1 = new Usuario();
        Usuario user2 = new Usuario();
        List<Usuario> users = List.of(user1, user2);
        List<UsuarioResponseDto> userDtos = List.of(
                new UsuarioResponseDto(user1.getId(), user1.getUsername(), "CLIENTE"),
                new UsuarioResponseDto(user2.getId(), user2.getUsername(), "CLIENTE")
        );

        given(usuarioService.buscarTodos()).willReturn(users);

        // WHEN
        ResponseEntity<List<UsuarioResponseDto>> response = usuarioController.getAll();

        // THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()));
        List<UsuarioResponseDto> responseBody = response.getBody();
        assertEquals(userDtos.size(), responseBody.size());
        for (int i = 0; i < userDtos.size(); i++) {
            assertEquals(userDtos.get(i).getId(), responseBody.get(i).getId());
            assertEquals(userDtos.get(i).getRole(), responseBody.get(i).getRole());
            assertEquals(userDtos.get(i).getUsername(), responseBody.get(i).getUsername());
        }
    }

    @Test
    @DisplayName("should create a new user and return with status code created")
    void shouldCreateNewUserAndReturnWithStatusCodeCreated() {
        // GIVEN
        UsuarioCreateDto createDto = new UsuarioCreateDto("testuser", "password");
        Usuario user = new Usuario(1L, "testuser", "password");
        UsuarioResponseDto userDto = new UsuarioResponseDto(user.getId(), user.getUsername(), "CLIENTE");

        given(usuarioService.salvar(any(Usuario.class))).willReturn(user);

        // WHEN
        ResponseEntity<UsuarioResponseDto> response = usuarioController.create(createDto);

        // THEN
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()));
        assertEquals(userDto.getId(), response.getBody().getId());
        assertEquals(userDto.getUsername(), response.getBody().getUsername());
        assertEquals(userDto.getRole(), response.getBody().getRole());
    }

    @Test
    @DisplayName("should return user by id with status code ok")
    void shouldReturnUserByIdWithStatusCodeOk() {
        // GIVEN
        Long userId = 1L;
        Usuario user = new Usuario(userId, "testuser", "password");
        UsuarioResponseDto userDto = new UsuarioResponseDto(user.getId(), user.getUsername(), "CLIENTE");

        given(usuarioService.buscarPorId(userId)).willReturn(user);

        // WHEN
        ResponseEntity<UsuarioResponseDto> response = usuarioController.getById(userId);

        // THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(Objects.requireNonNull(response.getBody()));
        assertEquals(userDto.getId(), response.getBody().getId());
        assertEquals(userDto.getUsername(), response.getBody().getUsername());
        assertEquals(userDto.getRole(), response.getBody().getRole());
    }
}