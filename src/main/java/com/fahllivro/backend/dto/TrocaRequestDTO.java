package com.fahllivro.backend.dto;

import com.fahllivro.backend.model.Book;
import com.fahllivro.backend.model.Troca;
import com.fahllivro.backend.model.User;
import com.fahllivro.backend.model.enums.StatusTroca;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrocaRequestDTO {

    @NotNull(message = "Usuário 1 é obrigatório")
    private Long user1Id;

    @NotNull(message = "Livro 1 é obrigatório")
    private Long book1Id;

    @NotNull(message = "Usuário 2 é obrigatório")
    private Long user2Id;

    @NotNull(message = "Livro 2 é obrigatório")
    private Long book2Id;

    @NotNull(message = "Status da troca é obrigatório")
    private StatusTroca status;

    public Troca toEntity(User user1, Book book1, User user2, Book book2) {
        return Troca.builder()
                .user1(user1)
                .book1(book1)
                .user2(user2)
                .book2(book2)
                .status(this.status)
                .build();
    }
}
