package com.backend.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    fechaNacimiento.set(2000, 10, 20);

    Calendar fechaVencimiento = Calendar.getInstance();
    fechaVencimiento.set(2021, 10, 20);
    fechaVencimiento.add(Calendar.DAY_OF_MONTH, 2);

    DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    String vencimiento_test = formatter.format(fechaVencimiento.getTime());

    String vencimiento =
        formatter.format(vigenciaService.getVigencia(fechaNacimiento.getTime(), true, false));

    assertEquals(vencimiento_test, vencimiento);

  }

  @Test
  public void testDiasEntreFechas() {
    Date todayDate = new Date();
    Calendar calendar1 = Calendar.getInstance();
    calendar1.setTime(todayDate);

    Calendar calendar2 = Calendar.getInstance();
    calendar2.set(2021, 10, 18);

    LocalDate localDate1 = LocalDateTime
        .ofInstant(calendar1.toInstant(), calendar1.getTimeZone().toZoneId()).toLocalDate();

    LocalDate localDate2 = LocalDateTime
        .ofInstant(calendar2.toInstant(), calendar2.getTimeZone().toZoneId()).toLocalDate();

    assertEquals(365, ChronoUnit.DAYS.between(localDate1, localDate2));
  }

}
