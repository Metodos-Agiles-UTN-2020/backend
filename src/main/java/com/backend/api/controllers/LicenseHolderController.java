package com.backend.api.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.backend.api.models.LicenseHolder;
import com.backend.api.services.LicenseHolderService;


@Component
@RestController
public class LicenseHolderController {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(LicenseHolderController.class);

  @Autowired
  private LicenseHolderService licenseHolderService;

  @PostMapping("/licenseholder")
  public void addLicenseHolder(@RequestBody LicenseHolder licenseHolder) {
    licenseHolderService.saveLicenseHolder(licenseHolder);
  }


}
