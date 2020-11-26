package com.backend.api.services;

import java.util.Calendar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.api.constants.CodigoLicencia;
import com.backend.api.models.Licencia;
import com.backend.api.models.TipoLicencia;
import com.backend.api.models.TitularLicencia;
import com.backend.api.repositories.LicenciaRepository;
import com.backend.api.repositories.TipoLicenciaRepository;

@Service
public class LicenciaService {
  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(LicenciaService.class);

  @Autowired
  private LicenciaRepository licenciaRepository;

  @Autowired
  private TitularLicenciaService titularLicenciaService;

  @Autowired
  private TipoLicenciaRepository tipoLicenciaRepository;

  public Licencia altaLicencia(Long idTitular, CodigoLicencia codigoLicencia, String limitaciones,
      String observaciones) throws Exception {
    // TODO
    System.out.println("hola1afafaaaaaaa");
    TitularLicencia titular = titularLicenciaService.getTitularById(idTitular);
    System.out.println("hola1afaf");
    TipoLicencia tipoLicencia = tipoLicenciaRepository.findByCodigo(codigoLicencia);
    System.out.println("holaasd1");

    if (tipoLicencia.getLicenciaProfesional()) {
      Calendar auxToday = Calendar.getInstance();
      auxToday.add(Calendar.YEAR, -1);
      Licencia tempLicencia = licenciaRepository
          .getLicenciaBByTitularAndFechaFinVigencia(titular.getId(), auxToday.getTime());
      if (tempLicencia == null) {
        System.out.println("hola1");
        throw new Exception();
      }
    }
    System.out.println("hola2");
    return new Licencia();
  }
}
