package com.backend.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.api.models.TitularLicencia;
import com.backend.api.repositories.TitularLicenciaRepository;

@Service
public class TitularLicenciaService {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(TitularLicenciaService.class);

  @Autowired
  private TitularLicenciaRepository titularLicenciaRepository;

  public TitularLicencia saveTitular(TitularLicencia titular) {
    return titularLicenciaRepository.save(titular);
  }

  public TitularLicencia getTitularByNroDocumento(String nroDocumento) {

    TitularLicencia titular = titularLicenciaRepository.findByNroDocumento(nroDocumento);

    return titular;
  }

}
