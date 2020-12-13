package com.backend.api.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.backend.api.constants.EstadoLicencia;
import com.backend.api.constants.FactorRh;
import com.backend.api.constants.GrupoSanguineo;
import com.backend.api.helper.AltaLicenciaRequest;
import com.backend.api.helper.AltaLicenciaResponse;
import com.backend.api.helper.LicenciaResponse;
import com.backend.api.helper.LicenseEncoder;
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


    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String fechafinVigencia = formatter.format(licencia.getFechaFinVigencia());
    String fechaInicioVigencia = formatter.format(licencia.getFechaInicioVigencia());
    String fechaNacimiento = formatter.format(licencia.getTitular().getFechaNacimiento());

    String licenciaFrente = LicenseEncoder.encodeFront(licencia.getTitular().getNroDocumento(),
        licencia.getTitular().getApellido(), licencia.getTitular().getNombre(),
        licencia.getTitular().getDomicilio(), fechaNacimiento, fechaInicioVigencia,
        licencia.getTipoLicencia().getCodigo().toString(), fechafinVigencia,
        licencia.getTitular().getFoto());


    String donante = licencia.getTitular().getDonante() ? "SI" : "NO";
    String factor = licencia.getTitular().getFactorRh().toString() == "POSITIVO" ? "+" : "-";

    String licenciaAtras = LicenseEncoder.encodeBack(licencia.getObservaciones(), donante,
        licencia.getTitular().getGrupoSanguineo().toString() + factor, licencia.getLimitaciones());


    AltaLicenciaResponse respuesta =
        new AltaLicenciaResponse(licencia, licenciaFrente, licenciaAtras);


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

}
