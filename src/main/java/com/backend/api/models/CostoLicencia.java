package com.backend.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import com.backend.api.constants.CodigoLicencia;

@Entity
public class CostoLicencia {

  @Id
  private CodigoLicencia tipo;
  private Integer costoAdministrativo;
  private Integer costoCopia;
  private Integer year5;
  private Integer year4;
  private Integer year3;
  private Integer year1;

  public CodigoLicencia getTipo() {
    return tipo;
  }

  public Integer getCostoAdministrativo() {
    return costoAdministrativo;
  }

  public void setCostoAdministrativo(Integer costoAdministrativo) {
    this.costoAdministrativo = costoAdministrativo;
  }

  public void setTipo(CodigoLicencia tipo) {
    this.tipo = tipo;
  }

  public Integer getYear5() {
    return year5;
  }

  public void setYear5(Integer year5) {
    this.year5 = year5;
  }

  public Integer getYear4() {
    return year4;
  }

  public void setYear4(Integer year4) {
    this.year4 = year4;
  }

  public Integer getYear3() {
    return year3;
  }

  public void setYear3(Integer year3) {
    this.year3 = year3;
  }

  public Integer getYear1() {
    return year1;
  }

  public void setYear1(Integer year1) {
    this.year1 = year1;
  }

  public Integer getCostoCopia() {
    return costoCopia;
  }

  public void setCostoCopia(Integer costoCopia) {
    this.costoCopia = costoCopia;
  }

}
