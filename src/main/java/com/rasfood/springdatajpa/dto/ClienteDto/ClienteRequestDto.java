package com.rasfood.springdatajpa.dto.ClienteDto;

import com.rasfood.springdatajpa.domain.model.ClienteId;
import com.rasfood.springdatajpa.domain.model.Contato;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDto {

    @Valid
    private ClienteId clienteId;

    @NotBlank(message = "Campo 'nome' não pode estar vazio")
    @Size(min = 3, max = 40, message = "Campo 'nome' deve ter entre {min} a {max} caracteres")
    private String nome;

    @Valid
    private Contato contato;
}
