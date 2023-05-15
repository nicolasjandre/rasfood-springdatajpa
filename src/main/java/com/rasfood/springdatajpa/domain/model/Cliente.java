package com.rasfood.springdatajpa.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cliente implements Serializable {

    public Cliente() {
        this.ordens = new ArrayList<>();
        this.enderecos = new ArrayList<>();
    }

    @EmbeddedId
    private ClienteId clienteId;

    private String nome;

    @Embedded
    private Contato contato;

    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference
    private List<Ordem> ordens;

    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference
    private List<Endereco> enderecos;
}