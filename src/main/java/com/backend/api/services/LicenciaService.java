package com.backend.api.services;

import java.util.Calendar;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.api.constants.CodigoLicencia;
import com.backend.api.constants.EstadoLicencia;
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

  @Autowired
  private VigenciaService vigenciaService;

  public Licencia altaLicencia(Long idTitular, CodigoLicencia codigoLicencia, String limitaciones,
      String observaciones) throws Exception {
    TitularLicencia titular = titularLicenciaService.getTitularById(idTitular);
    TipoLicencia tipoLicencia = tipoLicenciaRepository.findByCodigo(codigoLicencia);


    if (tipoLicencia.getLicenciaProfesional()) {
      Calendar auxToday = Calendar.getInstance();
      auxToday.add(Calendar.YEAR, -1);
      List<Licencia> tempLicencia =
          licenciaRepository.findByTitularAndTipoLicenciaAndFechaFinVigenciaLessThan(titular,
              tipoLicencia, auxToday.getTime());
      if (tempLicencia.isEmpty()) {
        throw new Exception();
      }

    }

    Calendar auxToday = Calendar.getInstance();

    Boolean tuvoLicencia;
    if (licenciaRepository.findByTitular(titular).isEmpty())
      tuvoLicencia = false;
    else
      tuvoLicencia = true;



    Licencia licencia = new Licencia();
    licencia.setEstado(EstadoLicencia.NOVIGENTE);
    licencia.setFechaFinVigencia(
        vigenciaService.getVigencia(titular.getFechaNacimiento(), tuvoLicencia, false));
    licencia.setFechaInicioVigencia(auxToday.getTime());
    licencia.setLimitaciones(limitaciones);
    licencia.setNumeroCopia(0);
    licencia.setObservaciones(observaciones);
    licencia.setTipoLicencia(tipoLicencia);
    licencia.setTitular(titular);

    return licenciaRepository.save(licencia);
  }
}
