package com.rasfood.springdatajpa.domain.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rasfood.springdatajpa.domain.exception.BadRequestException;
import com.rasfood.springdatajpa.domain.exception.NotFoundException;
import com.rasfood.springdatajpa.dto.ErrorDto.ErrorResponseDto;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> notFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto> dataIntegrityViolationException(DataIntegrityViolationException ex) {

        String errorMessage = ex.getMostSpecificCause().toString();

        if (ex.getMessage().contains("UKcmxo70m08n43599l3h0h07cc6")) {
            errorMessage = "Já existe um usuário cadastrado com este e-mail";
        } else if (ex.getMessage().contains("UK_bsrjojp50082lvlhi3vydc0x3")) {
            errorMessage = "Já existe um usuário cadastrado com este número de telefone";
        } else if (ex.getMessage().contains("UKr1u8010d60num5vc8fp0q1j2a")) {
            errorMessage = "Já existe um usuário cadastrado com este CPF";
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()
                .message(errorMessage)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> clienteAlreadyExistsException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> errorMessages = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();

            errorMessages.add(errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()
                .message(errorMessages.toString())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build());
    }
}
