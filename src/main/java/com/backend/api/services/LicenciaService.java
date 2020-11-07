package com.backend.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.api.repositories.LicenciaRepository;

@Service
public class LicenciaService {
  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(ApplicationUserDetailsService.class);

  @Autowired
  private LicenciaRepository licenciaRepository;
}
