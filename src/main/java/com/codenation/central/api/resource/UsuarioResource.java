package com.codenation.central.api.resource;

import com.codenation.central.api.dto.request.UsuarioRequest;
import com.codenation.central.api.dto.response.UsuarioResponse;
import com.codenation.central.api.handler.ErrorValidation;
import com.codenation.central.api.model.Usuario;
import com.codenation.central.api.service.interfaces.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(
            value = "Salvar um novo usuário",
            notes = "Essa operação salva um novo registro com informações do usuário"
    )
    @ApiResponses(value= {
            @ApiResponse(
                    code = 201,
                    message = "Retorna um UsuarioResponse com status code Created",
                    response = UsuarioResponse.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Retorna um erro de validação com status code Bad Request",
                    response = ErrorValidation.class
            )
    })
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
