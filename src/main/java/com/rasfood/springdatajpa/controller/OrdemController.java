package com.rasfood.springdatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasfood.springdatajpa.controller.CRUD.CRUDController;
import com.rasfood.springdatajpa.domain.service.OrdemService;
import com.rasfood.springdatajpa.dto.OrdemDto.OrdemRequestDto;
import com.rasfood.springdatajpa.dto.OrdemDto.OrdemResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordem")
public class OrdemController implements CRUDController<OrdemRequestDto, OrdemResponseDto, Long> {

    @Autowired
    private OrdemService ordemService;

    @Override
    @PostMapping
    public ResponseEntity<OrdemResponseDto> create(@Valid @RequestBody OrdemRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordemService.create(dto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ordemService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<OrdemResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(ordemService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OrdemResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ordemService.findById(id));
    }

    @Override
    public ResponseEntity<OrdemResponseDto> update(OrdemRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

}
