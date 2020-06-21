package com.codenation.central.api.service.interfaces;

import com.codenation.central.api.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EventoService {
    Evento salvar(Evento evento);
    Page<Evento> buscarEventosPorUsuario(Evento evento, Pageable pageable);
    Optional<Evento> buscarPorId(Long id);
}
