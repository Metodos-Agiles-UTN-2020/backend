package com.backend.api.models;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.backend.api.constants.FactorRh;
import com.backend.api.constants.GrupoSanguineo;
import com.backend.api.constants.TipoDocumento;

@Entity
public class TitularLicencia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String apellido;
  private String domicilio;
  private FactorRh factorRh;
  private Date fechaNacimiento;
  private GrupoSanguineo grupoSanguineo;
  private String nombre;
  private TipoDocumento tipoDocumento;
  private String nroDocumento;
  private Boolean donante;
  /* private Base64 foto; */

  /*
   * public Base64 getFoto() { return foto; }
   */

  /*
   * public TitularLicencia setFoto(Base64 foto) { this.foto = foto; return this; }
   */

  public TitularLicencia() {
    super();
  }

  public Boolean getDonante() {
    return donante;
  }

  public TitularLicencia setDonante(Boolean donante) {
    this.donante = donante;
    return this;
  }

  public long getId() {
    return id;
  }

  public TipoDocumento getTipoDocumento() {
    return tipoDocumento;
  }

  public TitularLicencia setTipoDocumento(TipoDocumento tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
    return this;
  }

  public TitularLicencia setId(long id) {
    this.id = id;
    return this;
  }

  public String getApellido() {
    return apellido;
  }

  public TitularLicencia setApellido(String apellido) {
    this.apellido = apellido;
    return this;
  }

  public String getDomicilio() {
    return domicilio;
  }

  public TitularLicencia setDomicilio(String domicilio) {
    this.domicilio = domicilio;
    return this;
  }

  public FactorRh getFactorRh() {
    return factorRh;
  }

  public TitularLicencia setFactorRh(FactorRh factorRh) {
    this.factorRh = factorRh;
    return this;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public TitularLicencia setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
    return this;
  }

  public GrupoSanguineo getGrupoSanguineo() {
    return grupoSanguineo;
  }

  public TitularLicencia setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
    this.grupoSanguineo = grupoSanguineo;
    return this;
  }

  public String getNombre() {
    return nombre;
  }

  public TitularLicencia setNombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  public String getNroDocumento() {
    return nroDocumento;
  }

  public TitularLicencia setNroDocumento(String nroDocumento) {
    this.nroDocumento = nroDocumento;
    return this;
  }

}
