package com.rasfood.springdatajpa.dto.CardapioDto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardapioResponseDto {

    private String nome;

    private String descricao;
    
    private String categoria;
    
    private BigDecimal valor;
}
