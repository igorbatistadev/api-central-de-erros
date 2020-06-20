package com.codenation.central.api.service.impl;

import com.codenation.central.api.model.Evento;
import com.codenation.central.api.repository.EventoRepository;
import com.codenation.central.api.service.interfaces.EventoService;
import com.codenation.central.api.util.UtilUsuarioAutenticado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public Evento salvar(Evento evento) {
        evento.setIdUsuario(UtilUsuarioAutenticado.getIdUsuarioAutenticado());
        return eventoRepository.save(evento);
    }

    @Override
    public Page<Evento> buscarEventosPorUsuario(Pageable pageable) {
        return eventoRepository.findAllByIdUsuario(UtilUsuarioAutenticado.getIdUsuarioAutenticado(), pageable);
    }

}
