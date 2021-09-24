package com.finki.websavings.domain.model.cashflow;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.util.Map;

/**
 * Model for the Expense Cash Flow.
 */
public class ExpenseCashFlowDomainModel extends CashFlowDomainModel {

  @Override
  public Map<Integer, AnnualValue> getAnnualValues(int numberOfYears) {
    return null;
  }
}