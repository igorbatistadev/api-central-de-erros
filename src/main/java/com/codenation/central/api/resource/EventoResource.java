package com.codenation.central.api.resource;

import com.codenation.central.api.dto.request.EventoRequest;
import com.codenation.central.api.dto.request.UsuarioRequest;
import com.codenation.central.api.dto.response.EventoResponse;
import com.codenation.central.api.dto.response.UsuarioResponse;
import com.codenation.central.api.model.Evento;
import com.codenation.central.api.model.Usuario;
import com.codenation.central.api.service.interfaces.EventoService;
import com.codenation.central.api.service.interfaces.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/evento")
public class EventoResource {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<Evento> salvar(@Valid @RequestBody EventoRequest eventoRequest) {
        Evento evento = eventoService.salvar(converterParaEvento(eventoRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
    }

    @GetMapping
    public ResponseEntity<Page<EventoResponse>> buscarEventosPorUsuario(EventoRequest eventoRequest,Pageable pageable) {
        Page<Evento> eventos = eventoService.buscarEventosPorUsuario(converterParaEvento(eventoRequest) ,pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(eventos.map(evento -> converterParaEventoResponse(evento)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventosPorUsuario(@PathVariable Long id) {
        Optional<Evento> eventoOptional = eventoService.buscarPorId(id);

        if (eventoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(eventoOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Evento converterParaEvento(EventoRequest eventoRequest) {
        return new ModelMapper().map(eventoRequest, Evento.class);
    }

    private EventoResponse converterParaEventoResponse(Evento evento) {
        return new ModelMapper().map(evento, EventoResponse.class);
    }
}
