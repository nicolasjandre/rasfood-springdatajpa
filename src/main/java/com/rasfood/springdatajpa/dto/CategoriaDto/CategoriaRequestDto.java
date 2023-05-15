package com.rasfood.springdatajpa.dto.CategoriaDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequestDto {

    private Long id;

    @NotBlank(message = "Campo 'nome' não pode estar vazio")
    private String nome;
}
