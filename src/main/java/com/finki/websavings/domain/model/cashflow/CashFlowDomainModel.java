package com.finki.websavings.domain.model.cashflow;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

/**
 * Domain model for the cash flow.
 */
@Getter
@Setter
public abstract class CashFlowDomainModel {

  private Integer id;
  private double value;
  private String currency;
  private String occurrenceType;
  private String description;
  private String category;
  private LocalDate dateFrom;
  private LocalDate dateTo;

  /**
   * Returns annual values calculation for the cash flow.
   *
   * @param numberOfYears the number of years.
   * @return the annual values for the project duration.
   */
  public abstract Map<Integer, AnnualValue> getAnnualValues(int numberOfYears);
}