package com.rasfood.springdatajpa.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Ordem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Cliente cliente;

    @Column(name = "data_do_pedido")
    private LocalDate dataDoPedido;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "ordem", cascade = CascadeType.ALL)
    private List<OrdemCardapio> ordemCardapios = new ArrayList<>();

    public Ordem() {
        this.valorTotal = BigDecimal.ZERO;
        this.dataDoPedido = LocalDate.now();
    }

    public Ordem(Cliente cliente) {
        this.valorTotal = BigDecimal.ZERO;
        this.dataDoPedido = LocalDate.now();
        this.cliente = cliente;
    }

    public void addOrdemCardapio(OrdemCardapio ordemCardapio) {
        ordemCardapio.setOrdem(this);
        this.ordemCardapios.add(ordemCardapio);
        this.valorTotal = this.valorTotal
                .add(ordemCardapio.getValor().multiply(BigDecimal.valueOf(ordemCardapio.getQuantidade())));
    }
}
