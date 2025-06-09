package com.fahllivro.backend.controller;

import com.fahllivro.backend.dto.UserRequestDTO;
import com.fahllivro.backend.dto.UserResponseDTO;
import com.fahllivro.backend.model.User;
import com.fahllivro.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO dto) {
        User user = userService.create(dto.toEntity());
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        return userService.getById(id)
                .map(user -> ResponseEntity.ok(new UserResponseDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getByEmail(@PathVariable String email) {
        return userService.getByEmail(email)
                .map(user -> ResponseEntity.ok(new UserResponseDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{nome}")
    public ResponseEntity<List<UserResponseDTO>> getByNome(@PathVariable String nome) {
        List<User> users = userService.getByNome(nome);
        List<UserResponseDTO> dtos = users.stream()
                .map(UserResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        List<UserResponseDTO> dtos = userService.getAll().stream()
                .map(UserResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO dto) {
        User updated = userService.update(id, dto.toEntity());
        return ResponseEntity.ok(new UserResponseDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
