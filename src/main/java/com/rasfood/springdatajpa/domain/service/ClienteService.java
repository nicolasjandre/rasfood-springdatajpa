package com.rasfood.springdatajpa.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasfood.springdatajpa.domain.exception.BadRequestException;
import com.rasfood.springdatajpa.domain.exception.NotFoundException;
import com.rasfood.springdatajpa.domain.model.Cliente;
import com.rasfood.springdatajpa.domain.model.ClienteId;
import com.rasfood.springdatajpa.domain.repository.ClienteRepository;
import com.rasfood.springdatajpa.domain.service.CRUD.CRUDService;
import com.rasfood.springdatajpa.dto.ClienteDto.ClienteRequestDto;
import com.rasfood.springdatajpa.dto.ClienteDto.ClienteResponseDto;

@Service
public class ClienteService implements CRUDService<ClienteRequestDto, ClienteResponseDto, ClienteId> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ClienteResponseDto create(ClienteRequestDto dto) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(dto.getClienteId());

        if (clienteOpt.isPresent()) {
            throw new BadRequestException("Já existe um cliente com este email e CPF");
        }

        Cliente cliente = clienteRepository.save(mapper.map(dto, Cliente.class));

        return mapper.map(cliente, ClienteResponseDto.class);
    }

    @Override
    public void delete(ClienteId id) {
        this.findById(id);
        clienteRepository.deleteById(id);
    }

    @Override
    public List<ClienteResponseDto> findAll() {
        return clienteRepository.findAll().stream()
                .map(cliente -> mapper.map(cliente, ClienteResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDto findById(ClienteId id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isEmpty()) {
            throw new NotFoundException("Cliente de id=[" + id + "] não encontrado");
        }

        return mapper.map(clienteOpt.get(), ClienteResponseDto.class);
    }

    public ClienteResponseDto findByCpfOrEmail(String id) {
        Optional<Cliente> clienteOpt = clienteRepository.findByCpfOrEmail(id);

        if (clienteOpt.isEmpty()) {
            throw new NotFoundException("Cliente de email ou cpf = [" + id + "] não encontrado");
        }

        return mapper.map(clienteOpt.get(), ClienteResponseDto.class);
    }

    @Override
    public ClienteResponseDto update(ClienteRequestDto dto) {
        this.findById(dto.getClienteId());

        Cliente cliente = clienteRepository.save(mapper.map(dto, Cliente.class));

        return mapper.map(cliente, ClienteResponseDto.class);
    }
}
