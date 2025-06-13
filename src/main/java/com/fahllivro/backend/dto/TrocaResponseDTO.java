package com.fahllivro.backend.dto;

import com.fahllivro.backend.model.Troca;
import com.fahllivro.backend.model.enums.StatusTroca;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrocaResponseDTO {

    private Long id;

    private Long user1Id;
    private String user1Nome;

    private Long book1Id;
    private String book1Titulo;

    private Long user2Id;
    private String user2Nome;

    private Long book2Id;
    private String book2Titulo;

    private StatusTroca status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TrocaResponseDTO(Troca troca) {
        this.id = troca.getId();

        this.user1Id = troca.getUser1().getId();
        this.user1Nome = troca.getUser1().getNome();

        this.book1Id = troca.getBook1().getId();
        this.book1Titulo = troca.getBook1().getTitulo();

        this.user2Id = troca.getUser2().getId();
        this.user2Nome = troca.getUser2().getNome();

        this.book2Id = troca.getBook2().getId();
        this.book2Titulo = troca.getBook2().getTitulo();

        this.status = troca.getStatus();
        this.createdAt = troca.getCreatedAt();
        this.updatedAt = troca.getUpdatedAt();
    }
}
