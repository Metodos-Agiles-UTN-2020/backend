package com.backend.api.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.backend.api.constants.EstadoLicencia;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Licencia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String observaciones;
  private String limitaciones;
  private Date fechaFinVigencia;
  private Date fechaInicioVigencia;
  private EstadoLicencia estado;
  private Integer numeroCopia;

  @OneToOne
  @JsonIgnore
  private TipoLicencia tipoLicencia;

  @OneToOne
  @JsonIgnore
  private TitularLicencia titular;

  public TitularLicencia getTitular() {
    return titular;
  }

  public void setTitular(TitularLicencia titular) {
    this.titular = titular;
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

  public TipoLicencia getTipoLicencia() {
    return tipoLicencia;
  }

  public void setTipoLicencia(TipoLicencia tipoLicencia) {
    this.tipoLicencia = tipoLicencia;
  }
}
