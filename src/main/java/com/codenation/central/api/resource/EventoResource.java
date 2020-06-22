package com.codenation.central.api.resource;

import com.codenation.central.api.dto.request.EventoRequest;
import com.codenation.central.api.dto.request.UsuarioRequest;
import com.codenation.central.api.dto.response.EventoResponse;
import com.codenation.central.api.dto.response.UsuarioResponse;
import com.codenation.central.api.handler.ErrorResponse;
import com.codenation.central.api.handler.ErrorValidation;
import com.codenation.central.api.handler.ResourceNotFoundException;
import com.codenation.central.api.model.Evento;
import com.codenation.central.api.model.Usuario;
import com.codenation.central.api.service.interfaces.EventoService;
import com.codenation.central.api.service.interfaces.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/eventos")
public class EventoResource {

    @Autowired
    private EventoService eventoService;

    @ApiOperation(
            value = "Salvar um novo evento",
            notes = "Essa operação salva um novo registro com evento de log"
    )
    @ApiResponses(value= {
            @ApiResponse(
                    code = 201,
                    message = "Retorna um evento com status code Created",
                    response = Evento.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Retorna um erro de validação com status code Bad Request",
                    response = ErrorValidation.class
            )
    })
    @PostMapping
    public ResponseEntity<Evento> salvar(@Valid @RequestBody EventoRequest eventoRequest) {
        Evento evento = eventoService.salvar(converterParaEvento(eventoRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
    }

    @ApiOperation(
            value = "Busca lista de eventos",
            notes = "Essa operação busca uma lista de eventos e tem suporte a filtragem, paginação e ordenação"
    )
    @ApiResponses(value= {
            @ApiResponse(
                    code = 200,
                    message = "Retorna um Page de evento com status code Success"
            )
    })
    @GetMapping
    public ResponseEntity<Page<EventoResponse>> buscarEventosPorUsuario(EventoRequest eventoRequest,Pageable pageable) {
        Page<Evento> eventos = eventoService.buscarEventosPorUsuario(converterParaEvento(eventoRequest) ,pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(eventos.map(evento -> converterParaEventoResponse(evento)));
    }

    @ApiOperation(
            value = "Busca evento por id",
            notes = "Essa operação busca uma evento por id e exibe também o campo log no response, campo esse que não é mostrado ao buscar uma lista de eventos"
    )
    @ApiResponses(value= {
            @ApiResponse(
                    code = 200,
                    message = "Retorna evento com status code Success"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Recurso não encontrado com status code Not Found",
                    response = ErrorResponse.class
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventosPorUsuario(@PathVariable Long id) {
        Evento evento = eventoService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento de id " + id + " não existe, ou não pertence ao usuário logado"));
        return ResponseEntity.status(HttpStatus.OK).body(evento);
    }

    private Evento converterParaEvento(EventoRequest eventoRequest) {
        return new ModelMapper().map(eventoRequest, Evento.class);
    }

    private EventoResponse converterParaEventoResponse(Evento evento) {
        return new ModelMapper().map(evento, EventoResponse.class);
    }
}
