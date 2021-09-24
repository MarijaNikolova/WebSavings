package com.finki.websavings.domain.model.cashflow;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.util.Map;

/**
 * Model for the Income Cash Flow.
 */
public class IncomeCashFlowDomainModel extends CashFlowDomainModel {

  @Override
  public Map<Integer, AnnualValue> getAnnualValues(int numberOfYears) {
    return null;
  }
}
