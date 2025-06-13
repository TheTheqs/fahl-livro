package com.fahllivro.backend.repository;

import com.fahllivro.backend.model.Troca;
import com.fahllivro.backend.model.User;
import com.fahllivro.backend.model.enums.StatusTroca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrocaRepository extends JpaRepository<Troca, Long> {

    // Trocas onde o user é o destinatário e o status é ABERTA
    List<Troca> findByUser2AndStatus(User user2, StatusTroca status);

    // Outros filtros úteis que talvez você vá querer depois:
    List<Troca> findByUser1(User user1);
    List<Troca> findByUser2(User user2);
}
