package com.backend.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import com.backend.api.helper.IdVigencia;

@Entity
@IdClass(IdVigencia.class)
public class Vigencia {
  @Id
  private Integer rangoInferiorEdad;
  @Id
  private Integer rangoSuperiorEdad;
  private Integer tiempoVigenciaPrimeraVez;
  private Integer tiempoVigencia;

  public Vigencia() {

  }

  public Integer getRangoInferiorEdad() {
    return rangoInferiorEdad;
  }

  public Integer getTiempoVigenciaPrimeraVez() {
    return tiempoVigenciaPrimeraVez;
  }

  public void setTiempoVigenciaPrimeraVez(Integer tiempoVigenciaPrimeraVez) {
    this.tiempoVigenciaPrimeraVez = tiempoVigenciaPrimeraVez;
  }

  public void setRangoInferiorEdad(Integer rangoInferiorEdad) {
    this.rangoInferiorEdad = rangoInferiorEdad;
  }

  public Integer getRangoSuperiorEdad() {
    return rangoSuperiorEdad;
  }

  public void setRangoSuperiorEdad(Integer rangoSuperiorEdad) {
    this.rangoSuperiorEdad = rangoSuperiorEdad;
  }

  public Integer getTiempoVigencia() {
    return tiempoVigencia;
  }

  public void setTiempoVigencia(Integer tiempoVigencia) {
    this.tiempoVigencia = tiempoVigencia;
  }

}
