package com.codenation.central.api.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UsuarioRequest {
    @NotNull(message = "Campo nome não pode ser nulo")
    @NotBlank(message = "Campo nome não pode estar vazio")
    private String nome;
    @NotNull(message = "Campo email não pode ser nulo")
    @NotBlank(message = "Campo email não pode estar vazio")
    @Email(message = "Campo email deve conter um endereço de e-mail bem formatado")
    private String email;
    @NotNull(message = "Campo senha não pode ser nulo")
    @NotBlank(message = "Campo senha nome não pode estar vazio")
    private String senha;
}
