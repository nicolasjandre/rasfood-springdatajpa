package com.rasfood.springdatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasfood.springdatajpa.controller.CRUD.CRUDController;
import com.rasfood.springdatajpa.domain.service.CardapioService;
import com.rasfood.springdatajpa.dto.CardapioDto.CardapioRequestDto;
import com.rasfood.springdatajpa.dto.CardapioDto.CardapioResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cardapio")
public class CardapioController implements CRUDController<CardapioRequestDto, CardapioResponseDto, Long> {

    @Autowired
    private CardapioService cardapioService;

    @Override
    @PostMapping
    public ResponseEntity<CardapioResponseDto> create(@Valid @RequestBody CardapioRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardapioService.create(dto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardapioService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CardapioResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(cardapioService.findAll());
    }

    @GetMapping("/pag")
    public ResponseEntity<Page<CardapioResponseDto>> findAllWithPagination(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(value = "sort", required = false) Sort.Direction sort,
            @RequestParam(value = "property", required = false) String property) {
        return ResponseEntity.status(HttpStatus.OK).body(cardapioService.findAllWithPagination(page, size, sort, property));
    }

    @GetMapping("/categoria")
    public ResponseEntity<Page<CardapioResponseDto>> findAllByCategoria(
            @RequestParam Long categoriaId,
            @RequestParam Integer page, @RequestParam Integer size,
            @RequestParam(value = "sort", required = false) Sort.Direction sort,
            @RequestParam(value = "property", required = false) String property) {
        return ResponseEntity.status(HttpStatus.OK).body(cardapioService.findAllByCategoria(categoriaId, page, size, sort, property));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CardapioResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cardapioService.findById(id));
    }

    @Override
    @PutMapping
    public ResponseEntity<CardapioResponseDto> update(@Valid @RequestBody CardapioRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardapioService.update(dto));
    }
}
