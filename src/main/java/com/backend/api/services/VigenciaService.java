package com.backend.api.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.api.exceptions.DateOutOfBoundException;
import com.backend.api.helper.Age;
import com.backend.api.repositories.VigenciaRepository;

@Service
public class VigenciaService {

  @Autowired
  VigenciaRepository vigenciaRepository;

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(VigenciaService.class);

  public Date getVigencia(Date fechaNacimiento, Boolean nuncaTuvoLicencia, Boolean expiro)
      throws Exception {

    Integer edad = 0;
    Integer vigencia = 0;
    Integer diasExtra = 0;
    Integer diasHastaFecha = 0;

    diasExtra = expiro ? 30 : 45;

    Calendar nacimientoACalendar = Calendar.getInstance();

    nacimientoACalendar.setTime(fechaNacimiento);

    edad = Age.getAge(fechaNacimiento);

    Calendar todayCalendar = Calendar.getInstance();

    Calendar cumpleaniosCalendar = Calendar.getInstance();

    cumpleaniosCalendar.setTime(fechaNacimiento);

    cumpleaniosCalendar.set(Calendar.YEAR, todayCalendar.get(Calendar.YEAR));

    if (todayCalendar.get(Calendar.DAY_OF_YEAR)
        - cumpleaniosCalendar.get(Calendar.DAY_OF_YEAR) > 0) {
      cumpleaniosCalendar.set(Calendar.YEAR, todayCalendar.get(Calendar.YEAR) + 1);
    }

    LocalDate localDate1 = LocalDateTime
        .ofInstant(todayCalendar.toInstant(), todayCalendar.getTimeZone().toZoneId()).toLocalDate();
    LocalDate localDate2 = LocalDateTime
        .ofInstant(cumpleaniosCalendar.toInstant(), cumpleaniosCalendar.getTimeZone().toZoneId())
        .toLocalDate();

    diasHastaFecha = Math.abs((int) ChronoUnit.DAYS.between(localDate1, localDate2));

    if (diasHastaFecha > diasExtra) {
      throw new DateOutOfBoundException();
    }

    if (nuncaTuvoLicencia) {
      vigencia = vigenciaRepository.tiempoVigenciaByEdadPrimeraVez(edad);
    } else {
      vigencia = vigenciaRepository.tiempoVigenciaByEdad(edad);
    }

    cumpleaniosCalendar.add(Calendar.YEAR, vigencia);

    System.out.println(vigencia);
    System.out.println(edad);

    Date date = cumpleaniosCalendar.getTime();

    return date;

  }
}
