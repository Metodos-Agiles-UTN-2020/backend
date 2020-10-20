package com.backend.api.models;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.backend.api.constants.FactorRh;
import com.backend.api.constants.GrupoSanguineo;
import com.backend.api.constants.TipoLicencia;

@Entity
public class LicenseHolder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String apellido;
  private TipoLicencia tipoLicencia;
  private String domicilio;
  private FactorRh factorRh;
  private Date fechaNacimiento;
  private GrupoSanguineo grupoSanguineo;
  private String nombre;
  private Integer nroDocumento;

  public LicenseHolder(String apellido, TipoLicencia tipoLicencia, String domicilio,
      FactorRh factorRh, Date fechaNacimiento, GrupoSanguineo grupoSanguineo, String nombre,
      Integer nroDocumento) {
    super();
    this.apellido = apellido;
    this.tipoLicencia = tipoLicencia;
    this.domicilio = domicilio;
    this.factorRh = factorRh;
    this.fechaNacimiento = fechaNacimiento;
    this.grupoSanguineo = grupoSanguineo;
    this.nombre = nombre;
    this.nroDocumento = nroDocumento;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public TipoLicencia getTipoLicencia() {
    return tipoLicencia;
  }

  public void setTipoLicencia(TipoLicencia tipoLicencia) {
    this.tipoLicencia = tipoLicencia;
  }

  public String getDomicilio() {
    return domicilio;
  }

  public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
  }

  public FactorRh getFactorRh() {
    return factorRh;
  }

  public void setFactorRh(FactorRh factorRh) {
    this.factorRh = factorRh;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public GrupoSanguineo getGrupoSanguineo() {
    return grupoSanguineo;
  }

  public void setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
    this.grupoSanguineo = grupoSanguineo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getNroDocumento() {
    return nroDocumento;
  }

  public void setNroDocumento(Integer nroDocumento) {
    this.nroDocumento = nroDocumento;
  }

}
