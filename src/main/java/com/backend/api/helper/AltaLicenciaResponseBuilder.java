package com.backend.api.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.backend.api.models.Licencia;

public class AltaLicenciaResponseBuilder {

  public static AltaLicenciaResponse build(Licencia licencia) {

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

    return respuesta;
  }
}
