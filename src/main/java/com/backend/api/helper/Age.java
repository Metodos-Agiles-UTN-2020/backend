package com.backend.api.helper;

import java.util.Calendar;
import java.util.Date;

public class Age {

  public static Integer getAge(Date fechaNacimiento) {
    Calendar today = Calendar.getInstance();
    Calendar birthDate = Calendar.getInstance();
    birthDate.setTime(fechaNacimiento);

    int todayYear = today.get(Calendar.YEAR);
    int birthDateYear = birthDate.get(Calendar.YEAR);

    int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
    int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);

    int todayMonth = today.get(Calendar.MONTH);
    int birthDateMonth = birthDate.get(Calendar.MONTH);

    int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
    int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);

    int age = todayYear - birthDateYear;

    if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)) {
      age--;

    } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)) {
      age--;
    }
    return age;
  }
}
