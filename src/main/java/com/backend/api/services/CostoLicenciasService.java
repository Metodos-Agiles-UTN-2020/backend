package com.backend.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.api.constants.CodigoLicencia;
import com.backend.api.models.CostoLicencias;
import com.backend.api.repositories.CostoLicenciasRepository;

@Service
public class CostoLicenciasService {

  @Autowired
  CostoLicenciasRepository costoLicenciasRepository;

  public Integer getCosto(CodigoLicencia codigo, Integer duracion) {
    CostoLicencias costoLicencias = costoLicenciasRepository.getOne(codigo);
    switch (duracion) {
      case 5:
        return costoLicencias.getYear5() + costoLicencias.getCostoAdministrativo();
      case 4:
        return costoLicencias.getYear4() + costoLicencias.getCostoAdministrativo();
      case 3:
        return costoLicencias.getYear3() + costoLicencias.getCostoAdministrativo();
      case 1:
        return costoLicencias.getYear1() + costoLicencias.getCostoAdministrativo();
    }
    return null;
  }
}
