package com.codenation.central.api.dto.request;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String nome;
    private String email;
    private String senha;
}
