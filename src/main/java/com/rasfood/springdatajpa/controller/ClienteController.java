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
import com.rasfood.springdatajpa.domain.model.ClienteId;
import com.rasfood.springdatajpa.domain.service.ClienteService;
import com.rasfood.springdatajpa.dto.ClienteDto.ClienteRequestDto;
import com.rasfood.springdatajpa.dto.ClienteDto.ClienteResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController implements CRUDController<ClienteRequestDto, ClienteResponseDto, ClienteId> {

    @Autowired
    private ClienteService clienteService;

    @Override
    @PostMapping
    public ResponseEntity<ClienteResponseDto> create(@Valid @RequestBody ClienteRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(dto));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody ClienteId id) {
        clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }

    @Override
    @GetMapping("/id")
    public ResponseEntity<ClienteResponseDto> findById(@RequestBody ClienteId id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> findByEmailOrId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findByCpfOrEmail(id));
    }

    @Override
    @PutMapping
    public ResponseEntity<ClienteResponseDto> update(@Valid @RequestBody ClienteRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.update(dto));
    }
}