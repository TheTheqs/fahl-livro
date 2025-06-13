package com.fahllivro.backend.repository;

import com.fahllivro.backend.model.Chamada;
import com.fahllivro.backend.model.enums.StatusChamada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadaRepository extends JpaRepository<Chamada, Long> {

    List<Chamada> findBySolicitanteContainingIgnoreCase(String solicitante);

    List<Chamada> findByStatus(StatusChamada status);
}
