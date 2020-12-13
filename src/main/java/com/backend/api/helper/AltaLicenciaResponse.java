package com.backend.api.helper;

import java.util.Date;
import com.backend.api.constants.EstadoLicencia;
import com.backend.api.models.Licencia;

public class AltaLicenciaResponse {
  private long id;
  private String observaciones;
  private String limitaciones;
  private Date fechaFinVigencia;
  private Date fechaInicioVigencia;
  private EstadoLicencia estado;
  private Integer numeroCopia;
  private Integer costo;
  private String licenciaFrente;
  private String licenciaAtras;


  public AltaLicenciaResponse(Licencia altaLicencia, String licenciaFrente, String licenciaAtras) {
    id = altaLicencia.getId();
    observaciones = altaLicencia.getObservaciones();
    limitaciones = altaLicencia.getLimitaciones();
    fechaFinVigencia = altaLicencia.getFechaFinVigencia();
    fechaInicioVigencia = altaLicencia.getFechaInicioVigencia();
    estado = altaLicencia.getEstado();
    numeroCopia = altaLicencia.getNumeroCopia();
    this.licenciaFrente = licenciaFrente;
    this.licenciaAtras = licenciaAtras;
  }

  public String getLicenciaFrente() {
    return licenciaFrente;
  }

  public void setLicenciaFrente(String licenciaFrente) {
    this.licenciaFrente = licenciaFrente;
  }

  public String getLicenciaAtras() {
    return licenciaAtras;
  }

  public void setLicenciaAtras(String licenciaAtras) {
    this.licenciaAtras = licenciaAtras;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

  public String getLimitaciones() {
    return limitaciones;
  }

  public void setLimitaciones(String limitaciones) {
    this.limitaciones = limitaciones;
  }

  public Date getFechaFinVigencia() {
    return fechaFinVigencia;
  }

  public void setFechaFinVigencia(Date fechaFinVigencia) {
    this.fechaFinVigencia = fechaFinVigencia;
  }

  public Date getFechaInicioVigencia() {
    return fechaInicioVigencia;
  }

  public void setFechaInicioVigencia(Date fechaInicioVigencia) {
    this.fechaInicioVigencia = fechaInicioVigencia;
  }

  public EstadoLicencia getEstado() {
    return estado;
  }

  public void setEstado(EstadoLicencia estado) {
    this.estado = estado;
  }

  public Integer getNumeroCopia() {
    return numeroCopia;
  }

  public void setNumeroCopia(Integer numeroCopia) {
    this.numeroCopia = numeroCopia;
  }

  public Integer getCosto() {
    return costo;
  }

  public void setCosto(Integer costo) {
    this.costo = costo;
  }
}
