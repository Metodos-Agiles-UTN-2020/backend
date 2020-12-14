package com.backend.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.backend.api.constants.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity


@SequenceGenerator(name = "seq", allocationSize = 1, initialValue = 2)
public class ApplicationUser {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
  private long id;
  @NotEmpty
  private String username;
  @JsonProperty(access = Access.WRITE_ONLY)
  @NotEmpty
  private String password;
  @NotEmpty
  private String nombre;
  @NotEmpty
  private String apellido;
  @NotEmpty
  private String dni;
  @NotEmpty
  private String mail;
  @NotNull
  private TipoUsuario tipoUsuario;

  public ApplicationUser() {
    super();
  }

  public ApplicationUser(String username, String password) {
    this.username = username;
    this.password = password;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public TipoUsuario getTipoUsuario() {
    return tipoUsuario;
  }

  public void setTipoUsuario(TipoUsuario tipoUsuario) {
    this.tipoUsuario = tipoUsuario;
  }

}
