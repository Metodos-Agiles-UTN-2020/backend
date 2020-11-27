package com.backend.api.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.backend.api.models.Licencia;
import com.backend.api.models.TipoLicencia;
import com.backend.api.models.TitularLicencia;

@Component
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {

  List<Licencia> findByTitularAndTipoLicenciaAndFechaFinVigenciaLessThan(TitularLicencia titular,
      TipoLicencia tipoLicencia, Date today);

  List<Licencia> findByTitular(TitularLicencia titular);
}
