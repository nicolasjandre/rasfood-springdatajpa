package com.rasfood.springdatajpa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rasfood.springdatajpa.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
