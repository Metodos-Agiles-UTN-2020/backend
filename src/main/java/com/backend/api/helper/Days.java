package com.backend.api.helper;

import java.util.Calendar;

public class Days {

  public static int daysBetween(Calendar day1, Calendar day2) {
    Calendar dayOne = (Calendar) day1.clone(), dayTwo = (Calendar) day2.clone();

    if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
      return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
    } else {
      if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
        Calendar temp = dayOne;
        dayOne = dayTwo;
        dayTwo = temp;
      }
      int extraDays = 0;

      int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

      while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
        dayOne.add(Calendar.YEAR, -1);
        extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
      }

      return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays;
    }
  }
}
