package com.codenation.central.api.util;


import com.codenation.central.api.model.Usuario;
import com.codenation.central.api.service.interfaces.UsuarioService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UtilUsuarioAutenticado {

    private static UsuarioService usuarioService;

    public UtilUsuarioAutenticado(UsuarioService usuarioService) {
        UtilUsuarioAutenticado.usuarioService = usuarioService;
    }

    public static Long getIdUsuarioAutenticado() {
        try {
            Optional<Usuario> usuarioOptional = usuarioService.buscarPorEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            if(usuarioOptional.isPresent()) {
                return usuarioOptional.get().getId();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}