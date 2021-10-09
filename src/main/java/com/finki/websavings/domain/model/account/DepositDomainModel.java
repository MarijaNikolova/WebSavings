package com.finki.websavings.domain.model.account;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Deposit domain model.
 */
public class DepositDomainModel extends SavingAccountDomainModel {

	@Override
	public Map<Integer, AnnualValue> getAnnualValues(int numberOfYears) {
		Map<Integer, AnnualValue> result = new HashMap<>();

		int year = LocalDate.now().getYear();

		AnnualValue initialAnnualValue = AnnualValue.builder().value(getInitialValue()).currency(getCurrency()).build();
		result.put(year, initialAnnualValue);

		for(int i = year + 1; i < year + numberOfYears + 1; ++i) {

			AnnualValue previousValue = result.get(i - 1);

			double currentValue = previousValue.getValue() * getGrowthRate();

			AnnualValue annualValue = AnnualValue.builder().value(currentValue).currency(getCurrency()).build();

			result.put(i, annualValue);
		}

		return result;
	}
}