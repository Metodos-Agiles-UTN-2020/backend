package com.backend.api.config;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import com.backend.api.models.TipoLicencia;
import com.backend.api.repositories.TipoLicenciaRepository;

@Converter
public class TipoLicenciaConverter implements AttributeConverter<TipoLicencia, Long> {

  @Autowired
  TipoLicenciaRepository tipoLicenciaRepository;

  @Override
  public Long convertToDatabaseColumn(TipoLicencia attribute) {
    return attribute.getId();
  }

  @Override
  public TipoLicencia convertToEntityAttribute(Long dbData) {
    return tipoLicenciaRepository.getOne(dbData);
  }

}
