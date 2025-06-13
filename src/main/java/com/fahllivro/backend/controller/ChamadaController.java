package com.fahllivro.backend.controller;

import com.fahllivro.backend.dto.ChamadaRequestDTO;
import com.fahllivro.backend.dto.ChamadaResponseDTO;
import com.fahllivro.backend.model.Chamada;
import com.fahllivro.backend.model.enums.StatusChamada;
import com.fahllivro.backend.service.ChamadaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamadas")
@RequiredArgsConstructor
public class ChamadaController {

    private final ChamadaService chamadaService;

    // POST - Criar chamada
    @PostMapping
    public ResponseEntity<ChamadaResponseDTO> create(@RequestBody @Valid ChamadaRequestDTO dto) {
        Chamada chamada = chamadaService.create(dto.toEntity());
        return ResponseEntity.ok(new ChamadaResponseDTO(chamada));
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ChamadaResponseDTO> getById(@PathVariable Long id) {
        return chamadaService.getById(id)
                .map(chamada -> ResponseEntity.ok(new ChamadaResponseDTO(chamada)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET - Listar todas
    @GetMapping
    public ResponseEntity<List<ChamadaResponseDTO>> getAll() {
        List<ChamadaResponseDTO> dtos = chamadaService.getAll().stream()
                .map(ChamadaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // GET - Buscar por solicitante (e-mail parcial)
    @GetMapping("/solicitante/{email}")
    public ResponseEntity<List<ChamadaResponseDTO>> getBySolicitante(@PathVariable String email) {
        List<ChamadaResponseDTO> dtos = chamadaService.getBySolicitante(email).stream()
                .map(ChamadaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // GET - Buscar por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ChamadaResponseDTO>> getByStatus(@PathVariable StatusChamada status) {
        List<ChamadaResponseDTO> dtos = chamadaService.getByStatus(status).stream()
                .map(ChamadaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // PUT - Atualizar chamada
    @PutMapping("/{id}")
    public ResponseEntity<ChamadaResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ChamadaRequestDTO dto) {
        Chamada updated = chamadaService.update(id, dto.toEntity());
        return ResponseEntity.ok(new ChamadaResponseDTO(updated));
    }

    // DELETE - Deletar chamada
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chamadaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
