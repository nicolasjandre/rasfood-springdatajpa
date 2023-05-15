package com.rasfood.springdatajpa.dto.CardapioDto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardapioRequestDto {

    private Long id;

    @NotBlank(message = "Campo 'nome' não pode estar vazio")
    @Size(min = 3, max = 25, message = "Campo 'nome' precisa ter entre {min} e {max} caracteres")
    private String nome;

    @NotBlank(message = "Campo 'descricao' não pode estar vazio")
    @Size(min = 5, max = 60, message = "Campo 'descricao' precisa ter entre 5 e 60 caracteres")
    private String descricao;

    @NotNull(message = "Campo 'disponivel' não pode ser nulo")
    private Boolean disponivel;

    @NotNull(message = "O campo 'categoriaId' não pode ser nulo")
    private Long categoriaId;

    @NotNull(message = "O campo 'valor' não pode ser nulo")
    private BigDecimal valor;
}
