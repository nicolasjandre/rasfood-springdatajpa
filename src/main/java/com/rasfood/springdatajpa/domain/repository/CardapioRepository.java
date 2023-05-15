package com.rasfood.springdatajpa.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rasfood.springdatajpa.domain.model.Cardapio;
import com.rasfood.springdatajpa.dto.CardapioDto.CardapioResponseDto;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {

    @Query(value = "SELECT " +
            "new com.rasfood.springdatajpa.dto.CardapioDto.CardapioResponseDto" +
            "(c.nome, c.descricao, c.categoria.nome, c.valor) " +
            "FROM Cardapio c WHERE c.categoria.id = :id AND c.disponivel = true")
    Page<CardapioResponseDto> findAllByCategoriaPaginated(@Param("id") Long categoriaId, Pageable pageable);

    @Query(value = "SELECT " +
            "new com.rasfood.springdatajpa.dto.CardapioDto.CardapioResponseDto" +
            "(c.nome, c.descricao, c.categoria.nome, c.valor) FROM Cardapio c")
    List<CardapioResponseDto> findAllWithCardapioResponseDto();

    @Query(value = "SELECT " +
            "new com.rasfood.springdatajpa.dto.CardapioDto.CardapioResponseDto" +
            "(c.nome, c.descricao, c.categoria.nome, c.valor) FROM Cardapio c")
    Page<CardapioResponseDto> findAllWithCardapioResponseDtoPaginated(Pageable pageable);

    @Query(value = "SELECT " +
            "new com.rasfood.springdatajpa.dto.CardapioDto.CardapioResponseDto" +
            "(c.nome, c.descricao, c.categoria.nome, c.valor) FROM Cardapio c " +
            "WHERE c.id = :id")
    Optional<CardapioResponseDto> findByIdWithCardapioResponseDto(@Param("id") Long id);
}
