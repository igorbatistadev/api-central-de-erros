package com.codenation.central.api.service.interfaces;

import com.codenation.central.api.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventoService {
    Evento salvar(Evento evento);
    Page<Evento> buscarEventosPorUsuario(Pageable pageable);
}
