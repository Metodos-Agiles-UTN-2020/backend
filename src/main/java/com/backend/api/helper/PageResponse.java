package com.backend.api.helper;

import java.util.List;
import org.springframework.data.domain.Page;

public class PageResponse<T, E> {
  private List<T> pagina;
  private Integer paginaActual;
  private Integer cantidadPaginas;
  private Integer cantidadResultadosTotales;

  public PageResponse(List<T> response, Page<E> pagina, Integer paginaActual) {
    this.pagina = response;
    this.paginaActual = paginaActual;
    this.cantidadPaginas = pagina.getTotalPages();
    this.cantidadResultadosTotales = (int) pagina.getTotalElements();
  }

  public List<T> getPagina() {
    return pagina;
  }

  public void setPagina(List<T> pagina) {
    this.pagina = pagina;
  }

  public Integer getPaginaActual() {
    return paginaActual;
  }

  public void setPaginaActual(Integer paginaActual) {
    this.paginaActual = paginaActual;
  }

  public Integer getCantidadPaginas() {
    return cantidadPaginas;
  }

  public void setCantidadPaginas(Integer cantidadPaginas) {
    this.cantidadPaginas = cantidadPaginas;
  }

  public Integer getCantidadResultadosTotales() {
    return cantidadResultadosTotales;
  }

  public void setCantidadResultadosTotales(Integer cantidadResultadosTotales) {
    this.cantidadResultadosTotales = cantidadResultadosTotales;
  }


}
