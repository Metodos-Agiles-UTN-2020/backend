package com.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.backend.api.constants.CodigoLicencia;
import com.backend.api.models.TipoLicencia;

@Component
public interface TipoLicenciaRepository extends JpaRepository<TipoLicencia, Long> {
  TipoLicencia findByCodigo(CodigoLicencia codigoLicencia);
}
