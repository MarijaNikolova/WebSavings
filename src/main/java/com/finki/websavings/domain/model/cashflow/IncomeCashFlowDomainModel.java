package com.finki.websavings.domain.model.cashflow;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Model for the Income Cash Flow.
 */
public class IncomeCashFlowDomainModel extends CashFlowDomainModel {

  /**
   * Check whether the cash flow from and to date are in the range of the current date until the number of years.
   * If the cash flow is one time, then create annual value with the complete value and add it to the income / expense
   * section.
   * If the cash flow is recurring, iterate through the range of today until the number of years and add the recurring
   * income / expense in the annual section.
   */
  @Override
  public Map<Integer, AnnualValue> getAnnualValues(int numberOfYears) {

    LocalDate localDate = LocalDate.now();
    int year = localDate.getYear();

    Map<Integer, AnnualValue> annualValueMap = new HashMap<>();

    if ("ONE-TIME".equals(getOccurrenceType())) {
      AnnualValue build = AnnualValue.builder().income(getValue()).build();
      annualValueMap.put(getDateFrom().getYear(), build);
    }

    for (int i = year; i < year + numberOfYears; i++) {

      AnnualValue value =
        AnnualValue
          .builder()
          .income(getValue())
          .currency(getCurrency()).build();

      annualValueMap.put(year, value);
    }

    return annualValueMap;
  }
}
