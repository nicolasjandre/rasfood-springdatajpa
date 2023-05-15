package com.rasfood.springdatajpa.controller.CRUD;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CRUDController<Req, Res, Id> {

    ResponseEntity<Res> create(Req dto);

    ResponseEntity<Res> update(Req dto);

    ResponseEntity<Void> delete(Id id);

    ResponseEntity<List<Res>> findAll();

    ResponseEntity<Res> findById(Id id);
}
