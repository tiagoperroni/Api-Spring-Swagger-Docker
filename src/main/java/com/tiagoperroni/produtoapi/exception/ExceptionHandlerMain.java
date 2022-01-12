package com.tiagoperroni.produtoapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerMain {
    
    @ExceptionHandler(ValidacaoDeCamposException.class)
    public ResponseEntity<ErrorMessage> validarCampos(ValidacaoDeCamposException validar) {
        var error = new ErrorMessage();
        error.setMessage(validar.getMessage());
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> objetoNaoEncontrado(ObjetoNaoEncontradoException objeto) {
        var error = new ErrorMessage();
        error.setMessage(objeto.getMessage());
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
