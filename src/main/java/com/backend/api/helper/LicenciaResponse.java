package com.backend.api.helper;

import java.util.Date;
import com.backend.api.constants.CodigoLicencia;
import com.backend.api.constants.FactorRh;
import com.backend.api.constants.GrupoSanguineo;
import com.backend.api.constants.TipoDocumento;
import com.backend.api.models.Licencia;

public class LicenciaResponse {
  private Long id;
  private String nombre;
  private String apellido;
  private CodigoLicencia codigoLicencia;
  private Date fechaFinVigencia;
  private TipoDocumento tipoDocumento;
  private String nroDocumento;
  private FactorRh factorRH;
  private GrupoSanguineo grupoSanguineo;
  private Boolean donante;

  public LicenciaResponse(Licencia licencia) {
    this.id = licencia.getId();
    this.nombre = licencia.getTitular().getNombre();
    this.apellido = licencia.getTitular().getApellido();
    this.codigoLicencia = licencia.getTipoLicencia().getCodigo();
    this.fechaFinVigencia = licencia.getFechaFinVigencia();
    this.tipoDocumento = licencia.getTitular().getTipoDocumento();
    this.nroDocumento = licencia.getTitular().getNroDocumento();
    this.factorRH = licencia.getTitular().getFactorRh();
    this.grupoSanguineo = licencia.getTitular().getGrupoSanguineo();
    this.donante = licencia.getTitular().getDonante();
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getFechaFinVigencia() {
    return fechaFinVigencia;
  }

  public void setFechaFinVigencia(Date fechaFinVigencia) {
    this.fechaFinVigencia = fechaFinVigencia;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public FactorRh getFactorRH() {
    return factorRH;
  }

  public void setFactorRH(FactorRh factorRH) {
    this.factorRH = factorRH;
  }

  public GrupoSanguineo getGrupoSanguineo() {
    return grupoSanguineo;
  }

  public void setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
    this.grupoSanguineo = grupoSanguineo;
  }

  public String getNroDocumento() {
    return nroDocumento;
  }

  public void setNroDocumento(String nroDocumento) {
    this.nroDocumento = nroDocumento;
  }

  public TipoDocumento getTipoDocumento() {
    return tipoDocumento;
  }

  public void setTipoDocumento(TipoDocumento tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  public CodigoLicencia getCodigoLicencia() {
    return codigoLicencia;
  }

  public void setCodigoLicencia(CodigoLicencia codigoLicencia) {
    this.codigoLicencia = codigoLicencia;
  }

  public Boolean getDonante() {
    return donante;
  }

  public void setDonante(Boolean donante) {
    this.donante = donante;
  }


}
