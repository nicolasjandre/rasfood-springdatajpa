package com.rasfood.springdatajpa.domain.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteId implements Serializable {

    @CPF(message = "Campo 'CPF' precisa conter um CPF válido")
    private String cpf;

    @Email(message = "Campo 'email' precisa conter um email válido")
    private String email;
}
