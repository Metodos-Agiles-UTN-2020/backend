package com.backend.api.models;

import javax.validation.constraints.NotNull;
import com.backend.api.constants.CodigoLicencia;

public class AltaLicenciaRequest {
  @NotNull
  public Long idTitular;
  @NotNull
  public CodigoLicencia codigoLicencia;
  public String limitaciones;
  public String observaciones;
}
