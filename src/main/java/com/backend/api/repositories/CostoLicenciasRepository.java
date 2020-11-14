package com.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.backend.api.constants.CodigoLicencia;
import com.backend.api.models.CostoLicencias;

@Component
public interface CostoLicenciasRepository extends JpaRepository<CostoLicencias, CodigoLicencia> {

}
