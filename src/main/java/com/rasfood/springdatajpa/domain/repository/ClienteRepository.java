package com.rasfood.springdatajpa.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rasfood.springdatajpa.domain.model.Cliente;
import com.rasfood.springdatajpa.domain.model.ClienteId;

public interface ClienteRepository extends JpaRepository<Cliente, ClienteId> {

    @Query("SELECT c FROM Cliente c WHERE c.clienteId.email = :id OR c.clienteId.cpf = :id")
    Optional<Cliente> findByCpfOrEmail(@Param("id") String id);
}
