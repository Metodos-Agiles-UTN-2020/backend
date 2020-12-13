package com.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.api.constants.CodigoLicencia;
import com.backend.api.models.CostoLicencia;
import com.backend.api.repositories.CostoLicenciaRepository;

@Service
public class CostoLicenciaService {

  @Autowired
  CostoLicenciaRepository costoLicenciaRepository;

  public Integer getCosto(CodigoLicencia codigo, Integer duracion) {
    CostoLicencia costoLicencia = costoLicenciaRepository.getOne(codigo);
    switch (duracion) {
      case 5:
        return costoLicencia.getYear5() + costoLicencia.getCostoAdministrativo();
      case 4:
        return costoLicencia.getYear4() + costoLicencia.getCostoAdministrativo();
      case 3:
        return costoLicencia.getYear3() + costoLicencia.getCostoAdministrativo();
      case 1:
        return costoLicencia.getYear1() + costoLicencia.getCostoAdministrativo();
    }
    return null;
  }

  public Integer getCostoCopia(CodigoLicencia codigo) {
    CostoLicencia costoLicencia = costoLicenciaRepository.getOne(codigo);

    return costoLicencia.getCostoCopia();
  }

}
