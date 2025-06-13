package com.fahllivro.backend.dto;

import com.fahllivro.backend.model.Chamada;
import com.fahllivro.backend.model.enums.StatusChamada;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChamadaResponseDTO {

    private Long id;
    private String solicitante;
    private String descricao;
    private StatusChamada status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ChamadaResponseDTO(Chamada chamada) {
        this.id = chamada.getId();
        this.solicitante = chamada.getSolicitante();
        this.descricao = chamada.getDescricao();
        this.status = chamada.getStatus();
        this.createdAt = chamada.getCreatedAt();
        this.updatedAt = chamada.getUpdatedAt();
    }
}
