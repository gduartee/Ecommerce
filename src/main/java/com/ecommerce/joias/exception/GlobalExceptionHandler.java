package com.ecommerce.joias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Esse método captura erros de validação (@NotBlank, @Size, etc)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {

        // Cria um objeto de erro padrão do Spring (RFC 7807)
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Erro de validação nos campos informados"
        );

        problemDetail.setTitle("Dados Inválidos");
        problemDetail.setProperty("timestamp", Instant.now());

        // Cria um mapa para listar: campo -> erro (ex: "password" -> "mínimo 6 caracteres")
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });

        // Adiciona a lista de campos errados na resposta
        problemDetail.setProperty("errors", fieldErrors);

        return problemDetail;
    }
}