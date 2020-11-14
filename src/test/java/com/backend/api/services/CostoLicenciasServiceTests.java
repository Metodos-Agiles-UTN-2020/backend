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
import com.backend.api.models.CostoLicencias;
import com.backend.api.repositories.CostoLicenciasRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CostoLicenciasServiceTests {

  @MockBean
  private CostoLicenciasRepository costoLicenciasRepository;

  @Autowired
  private CostoLicenciasService costoLicenciasService;

  @Test
  public void testGetCosto() {
    CostoLicencias costoLicencias = new CostoLicencias();
    costoLicencias.setTipo(CodigoLicencia.B);
    costoLicencias.setYear1(20);
    costoLicencias.setYear3(25);
    costoLicencias.setYear4(30);
    costoLicencias.setYear5(40);
    costoLicencias.setCostoAdministrativo(8);
    Mockito.when(costoLicenciasRepository.getOne(CodigoLicencia.B)).thenReturn(costoLicencias);
    assertEquals(38, costoLicenciasService.getCosto(CodigoLicencia.B, 4));
  }
}
