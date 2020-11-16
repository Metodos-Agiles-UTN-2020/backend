package com.backend.api.models;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.backend.api.constants.FactorRh;
import com.backend.api.constants.GrupoSanguineo;
import com.backend.api.constants.TipoDocumento;

@Entity
public class TitularLicencia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Size(min = 1, max = 30)
  @NotEmpty
  private String nombre;
  @Size(min = 1, max = 30)
  @NotEmpty
  private String apellido;
  @Size(min = 1, max = 255)
  @NotEmpty
  private String domicilio;
  @NotNull
  private FactorRh factorRh;
  @NotNull
  private Date fechaNacimiento;
  @NotNull
  private GrupoSanguineo grupoSanguineo;
  @NotNull
  private TipoDocumento tipoDocumento;
  @Size(min = 1, max = 30)
  @NotEmpty
  private String nroDocumento;
  @NotNull
  private Boolean donante;
  @NotEmpty
  private String foto;


  public String getFoto() {
    return foto;
  }

  public TitularLicencia setFoto(String foto) {
    this.foto = foto;
    return this;
  }


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
