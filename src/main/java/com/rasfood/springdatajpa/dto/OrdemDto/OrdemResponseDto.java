package com.rasfood.springdatajpa.dto.OrdemDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rasfood.springdatajpa.domain.model.Cliente;
import com.rasfood.springdatajpa.domain.model.OrdemCardapio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemResponseDto {
    
    private Long id;

    @JsonIgnoreProperties({"ordens", "enderecos"})
    private Cliente cliente;

    private LocalDate dataDoPedido;

    private BigDecimal valorTotal;

    @JsonIgnoreProperties("ordem")
    private List<OrdemCardapio> ordemCardapios;
}
