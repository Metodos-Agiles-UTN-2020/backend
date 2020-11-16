package com.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.backend.api.constants.CodigoLicencia;
import com.backend.api.models.CostoLicencia;

@Component
public interface CostoLicenciaRepository extends JpaRepository<CostoLicencia, CodigoLicencia> {

}
