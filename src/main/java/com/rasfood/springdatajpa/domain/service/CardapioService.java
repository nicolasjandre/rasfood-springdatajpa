package com.rasfood.springdatajpa.domain.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.rasfood.springdatajpa.domain.exception.BadRequestException;
import com.rasfood.springdatajpa.domain.exception.NotFoundException;
import com.rasfood.springdatajpa.domain.model.Cardapio;
import com.rasfood.springdatajpa.domain.model.Categoria;
import com.rasfood.springdatajpa.domain.repository.CardapioRepository;
import com.rasfood.springdatajpa.domain.service.CRUD.CRUDService;
import com.rasfood.springdatajpa.dto.CardapioDto.CardapioRequestDto;
import com.rasfood.springdatajpa.dto.CardapioDto.CardapioResponseDto;
import com.rasfood.springdatajpa.dto.CategoriaDto.CategoriaResponseDto;
import com.rasfood.springdatajpa.utils.PageableUtil;

@Service
public class CardapioService implements CRUDService<CardapioRequestDto, CardapioResponseDto, Long> {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CardapioResponseDto create(CardapioRequestDto dto) {

        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("Não pode haver um ID nessa requisição");
        }

        Cardapio cardapio = mapper.map(dto, Cardapio.class);

        CategoriaResponseDto categoriaResponseDto = categoriaService.findById(dto.getCategoriaId());

        cardapio.setCategoria(mapper.map(categoriaResponseDto, Categoria.class));

        cardapioRepository.save(cardapio);

        return mapper.map(cardapio, CardapioResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        cardapioRepository.deleteById(id);
    }

    @Override
    public List<CardapioResponseDto> findAll() {
        return cardapioRepository.findAllWithCardapioResponseDto();
    }

    @GetMapping("/pag")
    public Page<CardapioResponseDto> findAllWithPagination(Integer page, Integer size, Sort.Direction sort,
            String property) {
        Pageable pageable = PageableUtil.create(page, size, sort, property);
        return cardapioRepository.findAllWithCardapioResponseDtoPaginated(pageable);

    }

    public Page<CardapioResponseDto> findAllByCategoria(Long categoriaId, Integer page, Integer size,
            Sort.Direction sort, String property) {
        Pageable pageable = PageableUtil.create(page, size, sort, property);
        return cardapioRepository.findAllByCategoriaPaginated(categoriaId, pageable);
    }

    @Override
    public CardapioResponseDto findById(Long id) {
        Optional<CardapioResponseDto> cardapioOpt = cardapioRepository.findByIdWithCardapioResponseDto(id);

        if (cardapioOpt.isEmpty()) {
            throw new NotFoundException("Cardapio de id=[" + id + "] não encontrado");
        }

        return cardapioOpt.get();
    }

    @Override
    public CardapioResponseDto update(CardapioRequestDto dto) {
        this.findById(dto.getId());

        Cardapio cardapio = mapper.map(dto, Cardapio.class);

        CategoriaResponseDto categoriaResponseDto = categoriaService.findById(dto.getCategoriaId());

        cardapio.setCategoria(mapper.map(categoriaResponseDto, Categoria.class));

        cardapioRepository.save(cardapio);

        return mapper.map(cardapio, CardapioResponseDto.class);
    }
}
