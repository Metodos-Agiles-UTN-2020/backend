package com.backend.api.repositories;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import com.backend.api.models.Licencia;

@Component
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
  @Query(value = "SELECT * FROM licencia WHERE titular_id = ?1 " + "AND tipo_licencia = 1 "
      + "AND fecha_fin_vigencia < ?2", nativeQuery = true)
  public Licencia getLicenciaBByTitularAndFechaFinVigencia(Long idTitular, Date today);
}
