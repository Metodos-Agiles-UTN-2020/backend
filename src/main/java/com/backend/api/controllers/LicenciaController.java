package com.backend.api.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.backend.api.constants.EstadoLicencia;
import com.backend.api.constants.FactorRh;
import com.backend.api.constants.GrupoSanguineo;
import com.backend.api.helper.AltaLicenciaRequest;
import com.backend.api.helper.AltaLicenciaResponse;
import com.backend.api.helper.AltaLicenciaResponseBuilder;
import com.backend.api.helper.LicenciaResponse;
import com.backend.api.helper.PageResponse;
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

    Licencia licencia = licenciaService.altaLicencia(altaLicencia.idTitular,
        altaLicencia.codigoLicencia, altaLicencia.limitaciones, altaLicencia.observaciones);

    AltaLicenciaResponse respuesta = AltaLicenciaResponseBuilder.build(licencia);

    Calendar auxFinVigencia = Calendar.getInstance();
    auxFinVigencia.setTime(licencia.getFechaFinVigencia());

    Calendar auxInicioVigencia = Calendar.getInstance();
    auxInicioVigencia.setTime(licencia.getFechaInicioVigencia());

    respuesta.setCosto(costoLicenciaService.getCosto(licencia.getTipoLicencia().getCodigo(),
        auxFinVigencia.get(Calendar.YEAR) - auxInicioVigencia.get(Calendar.YEAR)));

    return respuesta;
  }

  @GetMapping("/licencias")
  PageResponse<LicenciaResponse, Licencia> getLicencias(
      @RequestParam(name = "nombre", required = false) String nombre,
      @RequestParam(name = "apellido", required = false) String apellido,
      @RequestParam(name = "grupoSanguineo", required = false) GrupoSanguineo grupoSanguineo,
      @RequestParam(name = "factorRH", required = false) FactorRh factorRH,
      @RequestParam(name = "donante", required = false) Boolean donante,
      @RequestParam(name = "expirada", required = false) Boolean expirada,
      @RequestParam(name = "pagina", required = false) Integer pagina,
      @RequestParam(name = "resultados", required = false) Integer resultados) {

    if (pagina == null) {
      pagina = 0;
    }

    if (resultados == null) {
      resultados = 10;
    }

    Pageable paginacion = PageRequest.of(pagina, resultados);

    Integer grupoSanguineoToInteger = null;
    Integer factorRHToInteger = null;


    if (grupoSanguineo != null) {
      grupoSanguineoToInteger = grupoSanguineo.ordinal();
    }

    if (factorRH != null) {
      factorRHToInteger = factorRH.ordinal();
    }

    ArrayList<LicenciaResponse> licenciaResponse = new ArrayList<LicenciaResponse>();
    Page<Licencia> paginaLicencias = null;

    Calendar today = Calendar.getInstance();

    if (expirada == Boolean.TRUE) {
      paginaLicencias = licenciaService.getLicenciasExpiradas(EstadoLicencia.NOVIGENTE,
          today.getTime(), paginacion);
    } else {
      paginaLicencias = licenciaService.getLicencias(nombre, apellido, grupoSanguineoToInteger,
          factorRHToInteger, donante, paginacion);
    }

    for (Licencia l : paginaLicencias) {
      licenciaResponse.add(new LicenciaResponse(l));
    }

    PageResponse<LicenciaResponse, Licencia> pageResponse =
        new PageResponse<LicenciaResponse, Licencia>(licenciaResponse, paginaLicencias, pagina);

    return pageResponse;
  }

  @GetMapping("licencia/copia/{id}")
  AltaLicenciaResponse getCopia(@PathVariable(value = "id") Long id) {

    Licencia licencia = licenciaService.getById(id);
    Integer nroCopia = licencia.getNumeroCopia() + 1;
    licenciaService.getCopia(id, nroCopia);
    licencia.setNumeroCopia(nroCopia);

    AltaLicenciaResponse respuesta = AltaLicenciaResponseBuilder.build(licencia);

    respuesta.setCosto(costoLicenciaService.getCostoCopia(licencia.getTipoLicencia().getCodigo()));

    return respuesta;

  }

  @PutMapping("/licencia")
  AltaLicenciaResponse renovarLicencia(
      @RequestParam(name = "modificada", required = false) Boolean modificada,
      @RequestParam(name = "expirada", required = false) Boolean expirada,
      @RequestBody Licencia licencia) throws Exception {

    if (modificada == null)
      modificada = false;
    if (expirada == null)
      expirada = false;


    if (licencia.getId() == 0) {
      throw new Exception();
    }

    licencia = licenciaService.modificarLicencia(modificada, expirada, licencia);

    AltaLicenciaResponse respuesta = AltaLicenciaResponseBuilder.build(licencia);

    Calendar auxFinVigencia = Calendar.getInstance();
    auxFinVigencia.setTime(licencia.getFechaFinVigencia());

    Calendar auxInicioVigencia = Calendar.getInstance();
    auxInicioVigencia.setTime(licencia.getFechaInicioVigencia());

    respuesta.setCosto(costoLicenciaService.getCosto(licencia.getTipoLicencia().getCodigo(),
        auxFinVigencia.get(Calendar.YEAR) - auxInicioVigencia.get(Calendar.YEAR)));

    return respuesta;
  }

}
