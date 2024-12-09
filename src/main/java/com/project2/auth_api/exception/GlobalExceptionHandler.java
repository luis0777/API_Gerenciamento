package com.project2.auth_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	public GlobalExceptionHandler() {
        System.out.println("GlobalExceptionHandler foi inicializado!");
    }
	
    // Captura qualquer erro não tratado
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedException(Exception ex) {
        // registrar detalhes do erro no log para análise interna
    	
    	System.out.println(ex.toString()); ;

        // Retornar a mensagem padrão para o cliente
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro inesperado. Contate o suporte.");
    }
}
