package com.codenation.central.api.dto.response;

import com.codenation.central.api.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EventoResponse {

    private Long id;
    private Long idUsuario;
    private Level level;
    private String descricao;
    private String origem;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;
    private Integer quantidade;
}
