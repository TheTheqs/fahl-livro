package com.fahllivro.backend.service;

import com.fahllivro.backend.model.User;
import com.fahllivro.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //C- Criar Usuário
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("E-mail já está em uso.");
        }

        // Criptografa a senha antes de salvar
        user.setSenha(passwordEncoder.encode(user.getSenha()));

        return userRepository.save(user);
    }

    //R- Read por id
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    //R- Read por email
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //R- Read por nome
    public List<User> getByNome(String nome) {
        return userRepository.findByNomeContainingIgnoreCase(nome);
    }

    //R- Read para todos os users
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //U- Atualizar usuário
    public User update(Long id, User user) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        existing.setNome(user.getNome());
        existing.setEmail(user.getEmail());

        // Se a senha foi alterada (ou sempre quiser recriptar), recriptografa
        existing.setSenha(passwordEncoder.encode(user.getSenha()));

        return userRepository.save(existing);
    }

    //D- Deletar usuário
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
