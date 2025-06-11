package com.fahllivro.backend.dto;

import com.fahllivro.backend.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<BookSimpleDTO> livros;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();

        if (user.getLivros() != null) {
            this.livros = user.getLivros().stream()
                    .map(BookSimpleDTO::new)
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class BookSimpleDTO {
        private Long id;
        private String titulo;
        private String autor;

        public BookSimpleDTO(com.fahllivro.backend.model.Book book) {
            this.id = book.getId();
            this.titulo = book.getTitulo();
            this.autor = book.getAutor();
        }
    }
}
