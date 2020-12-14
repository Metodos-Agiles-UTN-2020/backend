package com.backend.api.config;

import javax.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.backend.api.exceptions.AgeOutOfBoundsException;
import com.backend.api.exceptions.DateOutOfBoundException;
import com.backend.api.exceptions.ForbiddenException;
import com.backend.api.exceptions.MissingRequirementsException;
import com.backend.api.exceptions.NotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler({AgeOutOfBoundsException.class})
  public ResponseEntity<Object> handleAgeOutOfBoundsException(Exception ex, WebRequest request) {
    return new ResponseEntity<Object>(
        "El titular no cumple con los requisitos de edad para la accion solicitada",
        new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({DateOutOfBoundException.class})
  public ResponseEntity<Object> handleDateOutOfBoundsException(Exception ex, WebRequest request) {
    return new ResponseEntity<Object>("Fecha de solicitud de emision de licencia fuera de rango",
        new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ForbiddenException.class})
  public ResponseEntity<Object> handleForbiddenException(Exception ex, WebRequest request) {
    return new ResponseEntity<Object>(
        "El usuario no tiene los permisos necesarios para realizar la accion solicitada",
        new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({MissingRequirementsException.class})
  public ResponseEntity<Object> handleMissingRequirementsException(Exception ex,
      WebRequest request) {
    return new ResponseEntity<Object>(
        "El titular no cumple los requisitos para obtener una licencia profesional",
        new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({DataIntegrityViolationException.class})
  public ResponseEntity<Object> handleDataIntegrityViolationException(Exception ex,
      WebRequest request) {
    return new ResponseEntity<Object>("Campo unico repetido", new HttpHeaders(),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({EntityNotFoundException.class})
  public ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request) {
    return new ResponseEntity<Object>("El elemento solicitado no existe", new HttpHeaders(),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
    return new ResponseEntity<Object>("El elemento solicitado no existe o no tiene acceso al mismo",
        new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleInternalError(Exception ex, WebRequest request) {
    ex.printStackTrace();
    return new ResponseEntity<Object>(
        "Error interno del sistema, este error no es producto de una accion realizada por usted. Nuestros tecnicos estan trabajando para solucionarlo.",
        new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
