package com.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.backend.api.models.Licencia;

@Component
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
}
