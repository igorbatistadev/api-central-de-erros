package com.codenation.central.api.dto.request;

import com.codenation.central.api.model.enums.Level;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoRequest {

    private Level level;
    private String descricao;
    private String log;
    private String origem;
    private LocalDate data;
    private Integer quantidade;

}
