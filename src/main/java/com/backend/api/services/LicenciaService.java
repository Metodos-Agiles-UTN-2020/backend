package com.backend.api.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.backend.api.constants.CodigoLicencia;
import com.backend.api.constants.EstadoLicencia;
import com.backend.api.exceptions.AgeOutOfBoundsException;
import com.backend.api.exceptions.MissingRequirementsException;
import com.backend.api.helper.Age;
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


  public Licencia getById(Long id) {
    return licenciaRepository.getOne(id);
  }

  public Licencia altaLicencia(Long idTitular, CodigoLicencia codigoLicencia, String limitaciones,
      String observaciones) throws Exception {
    TitularLicencia titular = titularLicenciaService.getTitularById(idTitular);
    TipoLicencia tipoLicencia = tipoLicenciaRepository.findByCodigo(codigoLicencia);

    if (tipoLicencia.getLicenciaProfesional()) {
      Calendar auxToday = Calendar.getInstance();
      auxToday.add(Calendar.YEAR, -1);
      List<Licencia> tempLicencia =
          licenciaRepository.findByTitularAndTipoLicenciaAndFechaFinVigenciaLessThan(titular,
              tipoLicenciaRepository.findByCodigo(CodigoLicencia.B), auxToday.getTime());

      if (Age.getAge(titular.getFechaNacimiento()) > tipoLicencia.getEdadMaxima()
          || Age.getAge(titular.getFechaNacimiento()) < tipoLicencia.getEdadMinima()) {
        throw new AgeOutOfBoundsException();
      }

      if (tempLicencia.isEmpty()) {
        throw new MissingRequirementsException();
      }

    }


    Calendar auxToday = Calendar.getInstance();

    Boolean tuvoLicencia;
    if (licenciaRepository.findByTitular(titular).isEmpty())
      tuvoLicencia = false;
    else
      tuvoLicencia = true;

    Licencia licencia = new Licencia();
    licencia.setEstado(EstadoLicencia.VIGENTE);
    licencia.setFechaFinVigencia(
        vigenciaService.getVigencia(titular.getFechaNacimiento(), tuvoLicencia, false));
    licencia.setFechaInicioVigencia(auxToday.getTime());
    licencia.setLimitaciones(limitaciones);
    licencia.setNumeroCopia(0);
    licencia.setObservaciones(observaciones);
    licencia.setTipoLicencia(tipoLicencia);
    licencia.setTitular(titular);


    List<Licencia> licenciasAnteriores = licenciaRepository
        .findByTitularAndTipoLicenciaAndEstado(titular, tipoLicencia, EstadoLicencia.VIGENTE);


    if (licenciasAnteriores.size() != 0) {
      Licencia licenciaAnterior = licenciasAnteriores.get(0);
      licenciaAnterior.setEstado(EstadoLicencia.NOVIGENTE);
      licenciaRepository.save(licenciaAnterior);
    }

    return licenciaRepository.save(licencia);
  }


  public Page<Licencia> getLicencias(String nombre, String apellido, Integer grupoSanguineo,
      Integer factorRH, Boolean donante, Pageable paginacion) {
    return licenciaRepository.searchLicencias(nombre, apellido, grupoSanguineo, factorRH, donante,
        paginacion);
  }

  public Page<Licencia> getLicenciasExpiradas(EstadoLicencia estadoLicencia, Date today,
      Pageable paginacion) {
    return licenciaRepository.findByEstadoAndFechaFinVigenciaLessThanOrderByFechaFinVigenciaDesc(
        estadoLicencia, today, paginacion);
  }

  public void getCopia(Long id, Integer nroCopia) {
    licenciaRepository.copiaLicencia(id, nroCopia);
  }
}
