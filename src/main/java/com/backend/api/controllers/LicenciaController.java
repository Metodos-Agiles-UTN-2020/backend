package com.backend.api.controllers;

import java.util.Calendar;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.backend.api.models.AltaLicenciaRequest;
import com.backend.api.models.AltaLicenciaResponse;
import com.backend.api.models.Licencia;
import com.backend.api.services.CostoLicenciaService;
import com.backend.api.services.LicenciaService;

@Component
@RestController
public class LicenciaController {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(LicenciaController.class);

  @Autowired
  private LicenciaService licenciaService;

  @Autowired
  private CostoLicenciaService costoLicenciaService;

  @PostMapping("/licencia")
  AltaLicenciaResponse altaLicencia(@Valid @RequestBody AltaLicenciaRequest altaLicencia)
      throws Exception {

    Licencia licencia = null;
    AltaLicenciaResponse respuesta = null;

    try {
      licencia = licenciaService.altaLicencia(altaLicencia.idTitular, altaLicencia.codigoLicencia,
          altaLicencia.limitaciones, altaLicencia.observaciones);
      respuesta = new AltaLicenciaResponse(licencia);
    } catch (Exception e) {
      e.printStackTrace();
    }


    Calendar auxFinVigencia = Calendar.getInstance();
    auxFinVigencia.setTime(licencia.getFechaFinVigencia());

    Calendar auxInicioVigencia = Calendar.getInstance();
    auxInicioVigencia.setTime(licencia.getFechaInicioVigencia());

    respuesta.setCosto(costoLicenciaService.getCosto(licencia.getTipoLicencia().getCodigo(),
        auxFinVigencia.get(Calendar.YEAR) - auxInicioVigencia.get(Calendar.YEAR)));

    return respuesta;
  }


}
