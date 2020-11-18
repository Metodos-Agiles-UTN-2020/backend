package com.backend.api.config;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class IdVigencia implements Serializable {

  private Integer rangoInferiorEdad;
  private Integer rangoSuperiorEdad;

  public IdVigencia() {}

  public IdVigencia(Integer rangoInferiorEdad, Integer rangoSuperiorEdad) {
    this.rangoInferiorEdad = rangoInferiorEdad;
    this.rangoSuperiorEdad = rangoSuperiorEdad;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    IdVigencia idVigencia = (IdVigencia) o;
    return rangoInferiorEdad.equals(idVigencia.rangoInferiorEdad)
        && rangoSuperiorEdad.equals(idVigencia.rangoSuperiorEdad);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rangoInferiorEdad, rangoSuperiorEdad);
  }

}
