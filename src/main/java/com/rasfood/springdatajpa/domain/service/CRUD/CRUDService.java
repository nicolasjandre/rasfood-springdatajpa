package com.rasfood.springdatajpa.domain.service.CRUD;

import java.util.List;

public interface CRUDService<Req, Res, Id> {

    Res create(Req dto);

    Res update(Req dto);

    void delete(Id id);

    List<Res> findAll();

    Res findById(Id id);
}
