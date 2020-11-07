package com.backend.api.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.backend.api.constants.CodigoLicencia;

public class TipoLicencia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private CodigoLicencia codigo;
  private Integer edadMinima;
  private Integer edadMaxima;
  private Boolean licenciaProfesional;
  private CodigoLicencia[] licenciasNecesarias;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public CodigoLicencia getCodigo() {
    return codigo;
  }

  public void setCodigo(CodigoLicencia codigo) {
    this.codigo = codigo;
  }

  public Integer getEdadMinima() {
    return edadMinima;
  }

  public void setEdadMinima(Integer edadMinima) {
    this.edadMinima = edadMinima;
  }

  public Integer getEdadMaxima() {
    return edadMaxima;
  }

  public void setEdadMaxima(Integer edadMaxima) {
    this.edadMaxima = edadMaxima;
  }

  public Boolean getLicenciaProfesional() {
    return licenciaProfesional;
  }

  public void setLicenciaProfesional(Boolean licenciaProfesional) {
    this.licenciaProfesional = licenciaProfesional;
  }

  public CodigoLicencia[] getLicenciasNecesarias() {
    return licenciasNecesarias;
  }

  public void setLicenciasNecesarias(CodigoLicencia[] licenciasNecesarias) {
    this.licenciasNecesarias = licenciasNecesarias;
  }
}
