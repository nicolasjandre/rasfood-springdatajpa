package com.rasfood.springdatajpa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rasfood.springdatajpa.domain.model.Endereco;
import java.util.List;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByCep(String cep);
}
