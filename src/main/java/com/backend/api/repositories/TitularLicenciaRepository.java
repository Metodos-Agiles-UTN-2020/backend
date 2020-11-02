package com.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.backend.api.models.TitularLicencia;

@Component
public interface TitularLicenciaRepository extends JpaRepository<TitularLicencia, Long> {
  TitularLicencia findByNroDocumento(String nroDocumento);
}
