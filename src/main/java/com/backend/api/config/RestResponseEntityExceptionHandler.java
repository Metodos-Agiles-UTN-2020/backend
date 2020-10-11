package com.backend.api.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.backend.api.exceptions.NotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
    return new ResponseEntity<Object>("El elemento solicitado no existe o no tiene acceso al mismo",
        new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleInternalError(Exception ex, WebRequest request) {
    return new ResponseEntity<Object>(
        "Error interno del sistema, este error no es producto de una accion realizada por usted. Nuestros tecnicos estan trabajando para solucionarlo.",
        new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
