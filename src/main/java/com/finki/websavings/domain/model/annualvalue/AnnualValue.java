package com.finki.websavings.domain.model.annualvalue;

import lombok.Builder;
import lombok.Getter;

/**
 * Annual value calculation.
 */
@Getter
@Builder
public class AnnualValue {

  private final double value;
  private final String currency;
  private final double income;
  private final double expense;
}