package com.codenation.central.api.dto.request;

import com.codenation.central.api.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class EventoRequest {

    private Level level;
    @NotNull(message = "Campo descricao não pode ser nulo")
    @NotBlank(message = "Campo descricao não pode estar vazio")
    private String descricao;
    @NotNull(message = "Campo log não pode ser nulo")
    @NotBlank(message = "Campo log não pode estar vazio")
    private String log;
    @NotNull(message = "Campo origem não pode ser nulo")
    @NotBlank(message = "Campo origem não pode estar vazio")
    private String origem;
    @NotNull(message = "Campo data não pode ser nulo")
    @PastOrPresent(message = "Campo data deve estar no passado ou no presente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;
    @NotNull(message = "Campo quantidade não pode ser nulo")
    @Positive(message = "Campo quantidade deve ser um valor positivo")
    private Integer quantidade;

}
