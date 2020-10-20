package com.backend.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.api.models.LicenseHolder;
import com.backend.api.repositories.LicenseHolderRepository;

@Service
public class LicenseHolderService {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(LicenseHolderService.class);

  @Autowired
  private LicenseHolderRepository licenseHolderRepository;

  public LicenseHolder getLicenseHolder(Integer nroDocumento) {
    return licenseHolderRepository.findByNroDocumento(nroDocumento);
  }

  public void saveLicenseHolder(LicenseHolder licenseHolder) {
    licenseHolderRepository.save(licenseHolder);
  }

}
