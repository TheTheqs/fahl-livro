package com.fahllivro.backend.service;

import com.fahllivro.backend.model.Chamada;
import com.fahllivro.backend.model.enums.StatusChamada;
import com.fahllivro.backend.repository.ChamadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChamadaService {

    private final ChamadaRepository chamadaRepository;

    // C - Criar nova chamada
    public Chamada create(Chamada chamada) {
        return chamadaRepository.save(chamada);
    }

    // R - Buscar por ID
    public Optional<Chamada> getById(Long id) {
        return chamadaRepository.findById(id);
    }

    // R - Buscar todas
    public List<Chamada> getAll() {
        return chamadaRepository.findAll();
    }

    // R - Buscar por e-mail parcial (solicitante)
    public List<Chamada> getBySolicitante(String email) {
        return chamadaRepository.findBySolicitanteContainingIgnoreCase(email);
    }

    // R - Buscar por status
    public List<Chamada> getByStatus(StatusChamada status) {
        return chamadaRepository.findByStatus(status);
    }

    // U - Atualizar chamada
    public Chamada update(Long id, Chamada updated) {
        Chamada existing = chamadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamada não encontrada"));

        existing.setSolicitante(updated.getSolicitante());
        existing.setDescricao(updated.getDescricao());
        existing.setStatus(updated.getStatus());

        return chamadaRepository.save(existing);
    }

    // D - Deletar chamada
    public void delete(Long id) {
        chamadaRepository.deleteById(id);
    }
}
