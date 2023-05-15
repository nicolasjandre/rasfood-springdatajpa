package com.rasfood.springdatajpa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rasfood.springdatajpa.domain.model.Ordem;

public interface OrdemRepository extends JpaRepository<Ordem, Long>{
    
}
