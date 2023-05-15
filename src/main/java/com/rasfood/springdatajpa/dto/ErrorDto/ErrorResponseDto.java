package com.rasfood.springdatajpa.dto.ErrorDto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDto {

    private String message;
    private HttpStatus httpStatus;
    private Integer statusCode;
}
