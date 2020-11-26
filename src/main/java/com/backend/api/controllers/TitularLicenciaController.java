package com.backend.api.controllers;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.backend.api.constants.TipoDocumento;
import com.backend.api.models.TitularLicencia;
import com.backend.api.services.TitularLicenciaService;

@Component
@RestController
public class TitularLicenciaController {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(TitularLicenciaController.class);

  @Autowired
  private TitularLicenciaService titularLicenciaService;

  @PostMapping("/alta-titular")
  TitularLicencia altaTitular(@Valid @RequestBody TitularLicencia titular) {
    return titularLicenciaService.saveTitular(titular);
  }

  @GetMapping("/titular/{tipoDocumento}/{nroDocumento}")
  TitularLicencia getTitular(@PathVariable(value = "tipoDocumento") TipoDocumento tipoDocumento,
      @PathVariable(value = "nroDocumento") String nroDocumento) {

    return titularLicenciaService.getTitularByNroDocumentoAndTipoDocumento(nroDocumento,
        tipoDocumento);
  }
}
