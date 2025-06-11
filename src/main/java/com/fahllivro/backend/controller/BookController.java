package com.fahllivro.backend.controller;

import com.fahllivro.backend.dto.BookRequestDTO;
import com.fahllivro.backend.dto.BookResponseDTO;
import com.fahllivro.backend.model.Book;
import com.fahllivro.backend.model.User;
import com.fahllivro.backend.service.BookService;
import com.fahllivro.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    // POST - Criar livro
    @PostMapping
    public ResponseEntity<BookResponseDTO> create(@RequestBody @Valid BookRequestDTO dto) {
        User user = userService.getById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Book book = bookService.create(dto.toEntity(user));
        return ResponseEntity.ok(new BookResponseDTO(book));
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getById(@PathVariable Long id) {
        return bookService.getById(id)
                .map(book -> ResponseEntity.ok(new BookResponseDTO(book)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET - Buscar todos os livros
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAll() {
        List<BookResponseDTO> dtos = bookService.getAll().stream()
                .map(BookResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // GET - Buscar por título
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<BookResponseDTO>> getByTitulo(@PathVariable String titulo) {
        List<BookResponseDTO> dtos = bookService.getByTitulo(titulo).stream()
                .map(BookResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // GET - Buscar por autor
    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<BookResponseDTO>> getByAutor(@PathVariable String autor) {
        List<BookResponseDTO> dtos = bookService.getByAutor(autor).stream()
                .map(BookResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // GET - Buscar por título e autor
    @GetMapping("/busca")
    public ResponseEntity<List<BookResponseDTO>> getByTituloAndAutor(
            @RequestParam String titulo,
            @RequestParam String autor) {

        List<BookResponseDTO> dtos = bookService.getByTituloAndAutor(titulo, autor).stream()
                .map(BookResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // GET - Buscar por ID do usuário
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<BookResponseDTO>> getByUserId(@PathVariable Long userId) {
        List<BookResponseDTO> dtos = bookService.getByUserId(userId).stream()
                .map(BookResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // PUT - Atualizar livro
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> update(@PathVariable Long id, @RequestBody @Valid BookRequestDTO dto) {
        User user = userService.getById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Book updated = bookService.update(id, dto.toEntity(user));
        return ResponseEntity.ok(new BookResponseDTO(updated));
    }

    // DELETE - Deletar livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
