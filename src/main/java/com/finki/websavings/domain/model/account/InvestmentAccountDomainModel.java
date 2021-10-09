package com.finki.websavings.domain.model.account;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Investment account domain model.
 */
@Getter
@Setter
public class InvestmentAccountDomainModel extends SavingAccountDomainModel {

	private double riskFactor;

	@Override
	public Map<Integer, AnnualValue> getAnnualValues(int numberOfYears) {
		return new HashMap<>();
	}
}