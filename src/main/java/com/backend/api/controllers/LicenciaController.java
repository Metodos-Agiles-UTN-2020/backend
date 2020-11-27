package com.backend.api.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.backend.api.models.AltaLicencia;
import com.backend.api.models.Licencia;
import com.backend.api.services.LicenciaService;

@Component
@RestController
public class LicenciaController {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(LicenciaController.class);

  @Autowired
  private LicenciaService licenciaService;

  @PostMapping("/licencia")
  Licencia altaLicencia(@RequestBody AltaLicencia altaLicencia) throws Exception {
    return licenciaService.altaLicencia(altaLicencia.idTitular, altaLicencia.codigoLicencia,
        altaLicencia.limitaciones, altaLicencia.observaciones);
  }
}
