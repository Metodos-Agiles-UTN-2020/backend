package com.backend.api.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.backend.api.constants.CodigoLicencia;
import com.backend.api.services.CostoLicenciaService;

@Component
@RestController
public class CostoLicenciaController {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(CostoLicenciaController.class);

  @Autowired
  private CostoLicenciaService costoLicenciaService;

  @GetMapping("/costo-licencia/{codigo}/{duracion}")
  Integer getCostoLicencia(@PathVariable(value = "codigo") CodigoLicencia codigo,
      @PathVariable(value = "duracion") Integer duracion) {
    return costoLicenciaService.getCosto(codigo, duracion);
  }

}
