package com.backend.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.backend.api.constants.CodigoLicencia;
import com.backend.api.models.CostoLicencia;
import com.backend.api.repositories.CostoLicenciaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CostoLicenciaServiceTests {

  @MockBean
  private CostoLicenciaRepository costoLicenciaRepository;

  @Autowired
  private CostoLicenciaService costoLicenciaService;

  @Test
  public void testGetCosto() {
    CostoLicencia costoLicencia = new CostoLicencia();
    costoLicencia.setTipo(CodigoLicencia.B);
    costoLicencia.setYear1(20);
    costoLicencia.setYear3(25);
    costoLicencia.setYear4(30);
    costoLicencia.setYear5(40);
    costoLicencia.setCostoAdministrativo(8);
    Mockito.when(costoLicenciaRepository.getOne(CodigoLicencia.B)).thenReturn(costoLicencia);
    assertEquals(38, costoLicenciaService.getCosto(CodigoLicencia.B, 4));
  }
}
