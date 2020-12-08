package com.backend.api.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.api.constants.TipoDocumento;
import com.backend.api.helper.Age;
import com.backend.api.models.TitularLicencia;
import com.backend.api.repositories.TitularLicenciaRepository;

@Service
public class TitularLicenciaService {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(TitularLicenciaService.class);

  @Autowired
  private TitularLicenciaRepository titularLicenciaRepository;

  public TitularLicencia saveTitular(TitularLicencia titular) throws Exception {

    if (Age.getAge(titular.getFechaNacimiento()) < 18) {
      throw new Exception();
      // TODO agregar excepcion personalizada
    }
    return titularLicenciaRepository.save(titular);
  }

  public TitularLicencia getTitularByNroDocumentoAndTipoDocumento(String nroDocumento,
      TipoDocumento tipoDocumento) {

    TitularLicencia titular =
        titularLicenciaRepository.findByNroDocumentoAndTipoDocumento(nroDocumento, tipoDocumento);

    return titular;
  }

  public TitularLicencia getTitularById(Long id) {
    return titularLicenciaRepository.getOne(id);
  }

}
