package com.codenation.central.api.model;

import com.codenation.central.api.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    @Enumerated(EnumType.STRING)
    private Level level;
    private String descricao;
    private String log;
    private String origem;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate data;
    private Integer quantidade;

}
