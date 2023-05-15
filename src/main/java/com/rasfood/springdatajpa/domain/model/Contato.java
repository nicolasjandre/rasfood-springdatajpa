package com.rasfood.springdatajpa.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contato implements Serializable {

    @NotBlank(message = "Campo 'ddd' não pode estar vazio")
    private String ddd;

    @Column(unique = true)
    @NotBlank(message = "Campo 'numero' não pode estar vazio")
    private String numero;
}
