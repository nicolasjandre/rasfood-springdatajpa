package com.rasfood.springdatajpa.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasfood.springdatajpa.domain.exception.BadRequestException;
import com.rasfood.springdatajpa.domain.exception.NotFoundException;
import com.rasfood.springdatajpa.domain.model.Cliente;
import com.rasfood.springdatajpa.domain.model.Endereco;
import com.rasfood.springdatajpa.domain.repository.EnderecoRepository;
import com.rasfood.springdatajpa.domain.service.CRUD.CRUDService;
import com.rasfood.springdatajpa.dto.EnderecoDto.EnderecoRequestDto;
import com.rasfood.springdatajpa.dto.EnderecoDto.EnderecoResponseDto;

@Service
public class EnderecoService implements CRUDService<EnderecoRequestDto, EnderecoResponseDto, Long> {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public EnderecoResponseDto create(EnderecoRequestDto dto) {

        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("Não pode haver um ID nesta requisição");
        }

        Endereco endereco = mapper.map(dto, Endereco.class);
        Cliente cliente = mapper.map(clienteService.findById(dto.getClienteId()), Cliente.class);

        endereco.setCliente(cliente);
        enderecoRepository.save(endereco);

        return mapper.map(endereco, EnderecoResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        enderecoRepository.deleteById(id);
    }

    @Override
    public List<EnderecoResponseDto> findAll() {
        return enderecoRepository.findAll().stream()
                .map(endereco -> mapper.map(endereco, EnderecoResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EnderecoResponseDto findById(Long id) {
        Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);

        if (enderecoOpt.isEmpty()) {
            throw new NotFoundException("Endereço de id=[" + id + "] não encontrado");
        }

        return mapper.map(enderecoOpt.get(), EnderecoResponseDto.class);
    }

    public List<EnderecoResponseDto> findByCep(String cep) {
        return enderecoRepository.findByCep(cep).stream()
                .map(endereco -> mapper.map(endereco, EnderecoResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EnderecoResponseDto update(EnderecoRequestDto dto) {
        this.findById(dto.getId());

        if (Objects.nonNull(dto.getClienteId())) {
            throw new BadRequestException("Não pode haver um clienteId nesta requisição");
        }

        Endereco endereco = enderecoRepository.save(mapper.map(dto, Endereco.class));

        return mapper.map(endereco, EnderecoResponseDto.class);
    }
}