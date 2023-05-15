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
import com.rasfood.springdatajpa.domain.model.Cardapio;
import com.rasfood.springdatajpa.domain.model.Cliente;
import com.rasfood.springdatajpa.domain.model.Ordem;
import com.rasfood.springdatajpa.domain.model.OrdemCardapio;
import com.rasfood.springdatajpa.domain.repository.CardapioRepository;
import com.rasfood.springdatajpa.domain.repository.OrdemCardapioRepository;
import com.rasfood.springdatajpa.domain.repository.OrdemRepository;
import com.rasfood.springdatajpa.domain.service.CRUD.CRUDService;
import com.rasfood.springdatajpa.dto.OrdemDto.OrdemRequestDto;
import com.rasfood.springdatajpa.dto.OrdemDto.OrdemResponseDto;

@Service
public class OrdemService implements CRUDService<OrdemRequestDto, OrdemResponseDto, Long> {

    @Autowired
    private OrdemRepository ordemRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private OrdemCardapioRepository ordemCardapioRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public OrdemResponseDto create(OrdemRequestDto dto) {

        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("Não pode haver um ID nesta requisição");
        }

        Cliente cliente = mapper.map(clienteService.findById(dto.getClienteId()), Cliente.class);

        Ordem ordem = new Ordem(cliente);
        dto.getCardapios().forEach(cardapioDto -> {
            Optional<Cardapio> cardapioOpt = cardapioRepository.findById(cardapioDto.getCardapioId());

            if (cardapioOpt.isEmpty()) {
                throw new NotFoundException("Cardapio de id=[" + cardapioDto.getCardapioId() + "] não encontrado");
            }

            OrdemCardapio ordemCardapio = new OrdemCardapio();
            ordemCardapio.setCardapio(cardapioOpt.get());
            ordemCardapio.setValor(cardapioOpt.get().getValor());
            ordemCardapio.setQuantidade(cardapioDto.getQuantidade());

            ordem.addOrdemCardapio(ordemCardapio);
        });

        OrdemResponseDto ordemResponseDto = mapper.map(ordemRepository.save(ordem), OrdemResponseDto.class);
        ordem.getOrdemCardapios().forEach(ordemCardapio -> ordemCardapioRepository.save(ordemCardapio));
        return ordemResponseDto;
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        ordemRepository.deleteById(id);
    }

    @Override
    public List<OrdemResponseDto> findAll() {
        return ordemRepository.findAll().stream()
                .map(ordem -> mapper.map(ordem, OrdemResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrdemResponseDto findById(Long id) {
        Optional<Ordem> ordemOpt = ordemRepository.findById(id);

        if (ordemOpt.isEmpty()) {
            throw new NotFoundException("Ordem de id=[" + id + "] não encontrado");
        }

        return mapper.map(ordemOpt.get(), OrdemResponseDto.class);
    }

    @Override
    public OrdemResponseDto update(OrdemRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }
}
