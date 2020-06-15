package com.codenation.central.api.service.interfaces;

import com.codenation.central.api.model.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);
}
