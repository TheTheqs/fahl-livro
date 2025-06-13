package com.fahllivro.backend.service;

import com.fahllivro.backend.model.Troca;
import com.fahllivro.backend.model.User;
import com.fahllivro.backend.model.enums.StatusTroca;
import com.fahllivro.backend.repository.TrocaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrocaService {

    private final TrocaRepository trocaRepository;

    // C - Criar nova troca
    public Troca create(Troca troca) {
        return trocaRepository.save(troca);
    }

    // R - Buscar por ID
    public Optional<Troca> getById(Long id) {
        return trocaRepository.findById(id);
    }

    // R - Buscar todas
    public List<Troca> getAll() {
        return trocaRepository.findAll();
    }

    // R - Buscar trocas abertas onde user2 foi solicitado
    public List<Troca> getAbertasRecebidasPor(User user) {
        return trocaRepository.findByUser2AndStatus(user, StatusTroca.ABERTA);
    }

    // R - Buscar trocas onde o user1 propôs
    public List<Troca> getPropostasPor(User user) {
        return trocaRepository.findByUser1(user);
    }

    // R - Buscar trocas onde o user2 foi convidado
    public List<Troca> getRecebidasPor(User user) {
        return trocaRepository.findByUser2(user);
    }

    // U - Atualizar troca
    public Troca update(Long id, Troca updated) {
        Troca existing = trocaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Troca não encontrada"));

        existing.setUser1(updated.getUser1());
        existing.setBook1(updated.getBook1());
        existing.setUser2(updated.getUser2());
        existing.setBook2(updated.getBook2());
        existing.setStatus(updated.getStatus());

        return trocaRepository.save(existing);
    }

    // D - Deletar troca
    public void delete(Long id) {
        trocaRepository.deleteById(id);
    }
}
