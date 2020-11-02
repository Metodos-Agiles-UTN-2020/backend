package com.backend.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.backend.api.models.LicenseHolder;

@Component
public interface LicenseHolderRepository extends JpaRepository<LicenseHolder, Long> {
  LicenseHolder findByNroDocumento(Integer nroDocumento);
}
