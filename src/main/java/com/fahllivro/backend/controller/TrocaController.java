package com.fahllivro.backend.controller;

import com.fahllivro.backend.dto.TrocaRequestDTO;
import com.fahllivro.backend.dto.TrocaResponseDTO;
import com.fahllivro.backend.model.Book;
import com.fahllivro.backend.model.Troca;
import com.fahllivro.backend.model.User;
import com.fahllivro.backend.service.BookService;
import com.fahllivro.backend.service.TrocaService;
import com.fahllivro.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trocas")
@RequiredArgsConstructor
public class TrocaController {

    private final TrocaService trocaService;
    private final UserService userService;
    private final BookService bookService;

    // POST - Criar nova troca
    @PostMapping
    public ResponseEntity<TrocaResponseDTO> create(@RequestBody @Valid TrocaRequestDTO dto) {
        User user1 = userService.getById(dto.getUser1Id())
                .orElseThrow(() -> new RuntimeException("Usuário 1 não encontrado"));
        User user2 = userService.getById(dto.getUser2Id())
                .orElseThrow(() -> new RuntimeException("Usuário 2 não encontrado"));
        Book book1 = bookService.getById(dto.getBook1Id())
                .orElseThrow(() -> new RuntimeException("Livro 1 não encontrado"));
        Book book2 = bookService.getById(dto.getBook2Id())
                .orElseThrow(() -> new RuntimeException("Livro 2 não encontrado"));

        Troca troca = trocaService.create(dto.toEntity(user1, book1, user2, book2));
        return ResponseEntity.ok(new TrocaResponseDTO(troca));
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<TrocaResponseDTO> getById(@PathVariable Long id) {
        return trocaService.getById(id)
                .map(t -> ResponseEntity.ok(new TrocaResponseDTO(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET - Buscar todas
    @GetMapping
    public ResponseEntity<List<TrocaResponseDTO>> getAll() {
        List<TrocaResponseDTO> dtos = trocaService.getAll().stream()
                .map(TrocaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // GET - Trocas abertas recebidas por user2
    @GetMapping("/recebidas-abertas/{userId}")
    public ResponseEntity<List<TrocaResponseDTO>> getAbertasRecebidas(@PathVariable Long userId) {
        User user = userService.getById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<TrocaResponseDTO> dtos = trocaService.getAbertasRecebidasPor(user).stream()
                .map(TrocaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // GET - Trocas propostas por user1
    @GetMapping("/propostas/{userId}")
    public ResponseEntity<List<TrocaResponseDTO>> getPropostasPor(@PathVariable Long userId) {
        User user = userService.getById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<TrocaResponseDTO> dtos = trocaService.getPropostasPor(user).stream()
                .map(TrocaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // GET - Trocas recebidas por user2 (todas, não só abertas)
    @GetMapping("/recebidas/{userId}")
    public ResponseEntity<List<TrocaResponseDTO>> getRecebidasPor(@PathVariable Long userId) {
        User user = userService.getById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<TrocaResponseDTO> dtos = trocaService.getRecebidasPor(user).stream()
                .map(TrocaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // PUT - Atualizar troca
    @PutMapping("/{id}")
    public ResponseEntity<TrocaResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TrocaRequestDTO dto) {
        User user1 = userService.getById(dto.getUser1Id())
                .orElseThrow(() -> new RuntimeException("Usuário 1 não encontrado"));
        User user2 = userService.getById(dto.getUser2Id())
                .orElseThrow(() -> new RuntimeException("Usuário 2 não encontrado"));
        Book book1 = bookService.getById(dto.getBook1Id())
                .orElseThrow(() -> new RuntimeException("Livro 1 não encontrado"));
        Book book2 = bookService.getById(dto.getBook2Id())
                .orElseThrow(() -> new RuntimeException("Livro 2 não encontrado"));

        Troca updated = trocaService.update(id, dto.toEntity(user1, book1, user2, book2));
        return ResponseEntity.ok(new TrocaResponseDTO(updated));
    }

    // DELETE - Deletar troca
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trocaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
