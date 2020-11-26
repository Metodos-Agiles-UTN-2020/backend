package com.backend.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,
    reason = "Fecha de solicitud de emision de licencia fuera de rango")
public class DateOutOfBoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

}
