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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasfood.springdatajpa.controller.CRUD.CRUDController;
import com.rasfood.springdatajpa.domain.service.EnderecoService;
import com.rasfood.springdatajpa.dto.EnderecoDto.EnderecoRequestDto;
import com.rasfood.springdatajpa.dto.EnderecoDto.EnderecoResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController implements CRUDController<EnderecoRequestDto, EnderecoResponseDto, Long> {

    @Autowired
    private EnderecoService enderecoService;

    @Override
    @PostMapping
    public ResponseEntity<EnderecoResponseDto> create(@Valid @RequestBody EnderecoRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.create(dto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<EnderecoResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findById(id));
    }

    @GetMapping("/cep")
    public ResponseEntity<List<EnderecoResponseDto>> findByCep(@RequestParam String cep) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findByCep(cep));
    }

    @Override
    @PutMapping
    public ResponseEntity<EnderecoResponseDto> update(@Valid @RequestBody EnderecoRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.update(dto));
    }
}
