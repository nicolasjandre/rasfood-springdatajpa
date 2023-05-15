package com.rasfood.springdatajpa.dto.EnderecoDto;

import com.rasfood.springdatajpa.domain.model.ClienteId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequestDto {

    private Long id;

    @NotBlank(message = "Campo 'rua' não pode estar vazio")
    private String rua;

    @NotBlank(message = "Campo 'cidade' não pode estar vazio")
    private String cidade;

    @NotBlank(message = "Campo 'estado' não pode estar vazio")
    private String estado;

    @NotBlank(message = "Campo 'cep' não pode estar vazio")
    @Size(min = 8, max = 8, message = "Campo 'cep' precisa conter 8 algarismos")
    private String cep;

    @NotBlank(message = "Campo 'complemento' não pode estar vazio")
    private String complemento;

    private ClienteId clienteId;
}