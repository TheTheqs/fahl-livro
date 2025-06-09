package com.fahllivro.backend.repository;

import com.fahllivro.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    List<User> findByNomeContainingIgnoreCase(String nome);
    boolean existsByEmail(String email);
}
