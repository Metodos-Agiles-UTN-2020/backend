package com.backend.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No se encontro el elemento solicitado")
public class NotFoundException extends RuntimeException {

  /**
   * Esta excepcion se usa para devolver codigo http 404
   */
  private static final long serialVersionUID = 1L;

}
