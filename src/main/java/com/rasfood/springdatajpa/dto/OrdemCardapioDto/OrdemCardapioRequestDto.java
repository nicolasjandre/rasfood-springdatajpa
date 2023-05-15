package com.rasfood.springdatajpa.dto.OrdemCardapioDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemCardapioRequestDto {

    @NotNull(message = "Campo 'cardapioId' não pode ser nulo")
    private Long cardapioId;

    @NotNull(message = "Campo 'quantidade' não pode ser nulo")
    private Double quantidade;
}
