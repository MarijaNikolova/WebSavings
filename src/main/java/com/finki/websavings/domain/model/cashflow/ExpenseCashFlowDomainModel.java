package com.finki.websavings.domain.model.cashflow;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Model for the Expense Cash Flow.
 */
public class ExpenseCashFlowDomainModel extends CashFlowDomainModel {

  @Override
  public Map<Integer, AnnualValue> getAnnualValues(int numberOfYears) {

    LocalDate localDate = LocalDate.now();
    int year = localDate.getYear();

    Map<Integer, AnnualValue> annualValueMap = new HashMap<>();

    for (int i = year; i < year + numberOfYears; i++) {

      AnnualValue value =
        AnnualValue
          .builder()
          .expense(getValue())
          .currency(getCurrency()).build();

      annualValueMap.put(i, value);
    }

    return annualValueMap;
  }
}