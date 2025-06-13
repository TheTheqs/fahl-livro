package com.fahllivro.backend.dto;

import com.fahllivro.backend.model.Chamada;
import com.fahllivro.backend.model.enums.StatusChamada;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChamadaRequestDTO {

    @NotBlank(message = "E-mail do solicitante é obrigatório")
    @Email(message = "E-mail inválido")
    private String solicitante;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Status da chamada é obrigatório")
    private StatusChamada status;

    public Chamada toEntity() {
        return Chamada.builder()
                .solicitante(this.solicitante)
                .descricao(this.descricao)
                .status(this.status)
                .build();
    }
}
