package com.backend.api.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import com.backend.api.constants.EstadoLicencia;
import com.backend.api.models.Licencia;
import com.backend.api.models.TipoLicencia;
import com.backend.api.models.TitularLicencia;

@Component
public interface LicenciaRepository
    extends JpaRepository<Licencia, Long>, PagingAndSortingRepository<Licencia, Long> {

  List<Licencia> findByTitularAndTipoLicenciaAndFechaFinVigenciaLessThan(TitularLicencia titular,
      TipoLicencia tipoLicencia, Date today);

  List<Licencia> findByTitular(TitularLicencia titular);

  List<Licencia> findByTitularAndTipoLicenciaAndEstado(TitularLicencia titular,
      TipoLicencia tipoLicencia, EstadoLicencia estadoLicencia);


  @Query(value = "SELECT l.* FROM titular_licencia tl, licencia l WHERE tl.id = l.titular_id"
      + " AND (?1 is null or tl.nombre like cast(?1 as text))"
      + " AND (?2 is null or tl.apellido like cast(?2 as text))"
      + " AND (?3 is null or tl.grupo_sanguineo = cast(cast(?3 as text) as integer))"
      + " AND (?4 is null or tl.factor_rh = cast(cast(?4 as text) as integer))"
      + " AND (?5 is null or tl.donante = cast(cast(?5 as text) as boolean))" + " AND l.estado = 0",
      nativeQuery = true)
  Page<Licencia> searchLicencias(String nombre, String apellido, Integer grupoSanguineo,
      Integer factorRH, Boolean donante, Pageable pageable);

  Page<Licencia> findByEstadoAndFechaFinVigenciaLessThanOrderByFechaFinVigenciaDesc(
      EstadoLicencia estadoLicencia, Date today, Pageable pageable);

}


