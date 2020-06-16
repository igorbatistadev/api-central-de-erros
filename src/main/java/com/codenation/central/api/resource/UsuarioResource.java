package com.codenation.central.api.resource;

import com.codenation.central.api.dto.request.UsuarioRequest;
import com.codenation.central.api.dto.response.UsuarioResponse;
import com.codenation.central.api.model.Usuario;
import com.codenation.central.api.service.interfaces.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> salvar(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioService.salvar(converterParaUsuario(usuarioRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(converterParaUsuarioResponse(usuario));
    }

    private Usuario converterParaUsuario(UsuarioRequest usuarioRequest) {
        return new ModelMapper().map(usuarioRequest, Usuario.class);
    }

    private UsuarioResponse converterParaUsuarioResponse(Usuario usuario) {
        return new ModelMapper().map(usuario, UsuarioResponse.class);
    }

}
