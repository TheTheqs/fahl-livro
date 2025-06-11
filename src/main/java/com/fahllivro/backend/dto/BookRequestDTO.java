package com.fahllivro.backend.dto;

import com.fahllivro.backend.model.Book;
import com.fahllivro.backend.model.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 0, message = "Quantidade deve ser igual ou maior que 0")
    private Integer quantidade;

    @NotNull(message = "ID do usuário é obrigatório")
    private Long userId;

    public Book toEntity(User user) {
        return Book.builder()
                .titulo(this.titulo)
                .autor(this.autor)
                .descricao(this.descricao)
                .quantidade(this.quantidade)
                .user(user)
                .build();
    }
}
