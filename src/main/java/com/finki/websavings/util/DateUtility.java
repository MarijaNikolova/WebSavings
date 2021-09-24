package com.finki.websavings.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Date Utility methods.
 */
public final class DateUtility {

  private static final String ISO_DATE_FORMAT = "yyyy-MM-dd";

  private DateUtility() {

  }

  /**
   * Creates a {@link LocalDate} object out of the given date string. The input should be in ISO format:
   * yyyy-MM-dd.
   *
   * @param isoDateString ISO date string (yyyy-MM-dd)
   * @return new date object
   * @throws RuntimeException if the string is in incorrect format
   */
  public static LocalDate fromIsoDateString(String isoDateString) {
    DateTimeFormatter format = DateTimeFormatter.ISO_DATE;

    return LocalDate.parse(isoDateString, format);
  }

  /**
   * Converts the given date to string in the ISO Date format(2017-02-22).
   *
   * @param date the date.
   * @return the string representation of the date.
   */
  public static String convertDateToIsoStringFormat(LocalDate date) {
    return date.format(DateTimeFormatter.ISO_DATE);
  }
}
