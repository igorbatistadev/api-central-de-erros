package com.codenation.central.api.repository;

import com.codenation.central.api.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    Page<Evento> findAllByIdUsuario(Long idUsuario, Pageable pageable);
}
