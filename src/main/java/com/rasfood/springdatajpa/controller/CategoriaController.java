package com.rasfood.springdatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasfood.springdatajpa.controller.CRUD.CRUDController;
import com.rasfood.springdatajpa.domain.service.CategoriaService;
import com.rasfood.springdatajpa.dto.CategoriaDto.CategoriaRequestDto;
import com.rasfood.springdatajpa.dto.CategoriaDto.CategoriaResponseDto;

@RestController
@RequestMapping("/categoria")
public class CategoriaController implements CRUDController<CategoriaRequestDto, CategoriaResponseDto, Long> {

    @Autowired
    private CategoriaService categoriaService;

    @Override
    @PostMapping
    public ResponseEntity<CategoriaResponseDto> create(@RequestBody CategoriaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.create(dto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.findById(id));
    }

    @Override
    @PutMapping
    public ResponseEntity<CategoriaResponseDto> update(@RequestBody CategoriaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.update(dto));
    }
}
