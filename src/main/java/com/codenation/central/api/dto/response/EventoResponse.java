package com.codenation.central.api.dto.response;

import com.codenation.central.api.model.enums.Level;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoResponse {

    private Long id;
    private Long idUsuario;
    private Level level;
    private String descricao;
    private String origem;
    private LocalDate data;
    private Integer quantidade;
}
