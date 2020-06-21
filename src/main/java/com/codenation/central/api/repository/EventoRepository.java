package com.codenation.central.api.repository;

import com.codenation.central.api.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    Optional<Evento> findByIdAndIdUsuarioEquals(Long id, Long idUsuario);
}
