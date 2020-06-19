package com.codenation.central.api.model;

import com.codenation.central.api.model.enums.Level;
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
    private Level level;
    private String descricao;
    private String log;
    private String origem;
    private LocalDate data;
    private Integer quantidade;

}
