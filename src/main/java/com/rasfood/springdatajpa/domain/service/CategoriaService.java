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
import com.rasfood.springdatajpa.domain.model.Categoria;
import com.rasfood.springdatajpa.domain.repository.CategoriaRepository;
import com.rasfood.springdatajpa.domain.service.CRUD.CRUDService;
import com.rasfood.springdatajpa.dto.CategoriaDto.CategoriaRequestDto;
import com.rasfood.springdatajpa.dto.CategoriaDto.CategoriaResponseDto;

@Service
public class CategoriaService implements CRUDService<CategoriaRequestDto, CategoriaResponseDto, Long> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoriaResponseDto create(CategoriaRequestDto dto) {
        
        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("Não pode haver um ID nesta requisição");
        }

        Categoria categoria = categoriaRepository.save(mapper.map(dto, Categoria.class));

        return mapper.map(categoria, CategoriaResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        categoriaRepository.deleteById(id);
    }

    @Override
    public List<CategoriaResponseDto> findAll() {
        return categoriaRepository.findAll().stream()
        .map(categoria -> mapper.map(categoria, CategoriaResponseDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public CategoriaResponseDto findById(Long id) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);

        if (categoriaOpt.isEmpty()) {
            throw new NotFoundException("Categoria de id=[" + id + "] não encontrado");
        }

        return mapper.map(categoriaOpt.get(), CategoriaResponseDto.class);
    }

    @Override
    public CategoriaResponseDto update(CategoriaRequestDto dto) {
        this.findById(dto.getId());

        Categoria categoria = categoriaRepository.save(mapper.map(dto, Categoria.class));

        return mapper.map(categoria, CategoriaResponseDto.class);
    }

}
