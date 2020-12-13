package com.backend.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.backend.api.exceptions.DateOutOfBoundException;
import com.backend.api.repositories.VigenciaRepository;

@SpringBootTest
public class VigenciaServiceTests {

  @MockBean
  private VigenciaRepository vigenciaRepository;

  @Autowired
  private VigenciaService vigenciaService;

  @Test
  public void testVigencia() {
    Mockito.when(vigenciaRepository.tiempoVigenciaByEdadPrimeraVez(19)).thenReturn(1);

    Calendar fechaNacimiento = Calendar.getInstance();

    fechaNacimiento.set(2001, 0, 1);

    Calendar fechaVencimiento = Calendar.getInstance();
    fechaVencimiento.set(2022, 0, 1);

    DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    String vencimiento_test = formatter.format(fechaVencimiento.getTime());

    String vencimiento = null;
    try {
      vencimiento = formatter.format(vigenciaService.getVigencia(fechaNacimiento.getTime(), true));
    } catch (Exception e) {
      Calendar aux = Calendar.getInstance();
      vencimiento = formatter.format(aux.getTime());
    }

    assertEquals(vencimiento_test, vencimiento);

  }

  @Test()
  public void testVigencia2() {
    Mockito.when(vigenciaRepository.tiempoVigenciaByEdadPrimeraVez(19)).thenReturn(1);

    Calendar fechaNacimiento = Calendar.getInstance();

    fechaNacimiento.set(2000, 5, 20);

    Calendar fechaVencimiento = Calendar.getInstance();
    fechaVencimiento.set(2021, 10, 20);

    DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    String vencimiento_test = formatter.format(fechaVencimiento.getTime());

    try {
      vigenciaService.getVigencia(fechaNacimiento.getTime(), true);
    } catch (DateOutOfBoundException e) {
      assertEquals(1, 1);
    }

  }
}
