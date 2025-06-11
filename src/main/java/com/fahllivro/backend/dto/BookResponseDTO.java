package com.fahllivro.backend.dto;

import com.fahllivro.backend.model.Book;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String descricao;
    private Integer quantidade;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Dados mínimos do usuário dono do livro
    private Long userId;
    private String userNome;

    public BookResponseDTO(Book book) {
        this.id = book.getId();
        this.titulo = book.getTitulo();
        this.autor = book.getAutor();
        this.descricao = book.getDescricao();
        this.quantidade = book.getQuantidade();
        this.createdAt = book.getCreatedAt();
        this.updatedAt = book.getUpdatedAt();

        if (book.getUser() != null) {
            this.userId = book.getUser().getId();
            this.userNome = book.getUser().getNome();
        }
    }
}
