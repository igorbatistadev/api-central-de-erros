package com.codenation.central.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataCriacao;

}
