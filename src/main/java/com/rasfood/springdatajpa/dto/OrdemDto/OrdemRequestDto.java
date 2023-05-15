package com.rasfood.springdatajpa.dto.OrdemDto;

import java.util.List;

import com.rasfood.springdatajpa.domain.model.ClienteId;
import com.rasfood.springdatajpa.dto.OrdemCardapioDto.OrdemCardapioRequestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemRequestDto {
    
    private Long id;

    @NotNull(message = "Campo 'clienteId' não pode ser nulo")
    private ClienteId clienteId;

    @NotNull(message = "Campo 'ordemCardapios' não pode ser nulo")
    private List<OrdemCardapioRequestDto> cardapios;
}
