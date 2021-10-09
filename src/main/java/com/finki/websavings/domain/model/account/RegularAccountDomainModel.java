package com.finki.websavings.domain.model.account;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Regular account domain model.
 */
public class RegularAccountDomainModel extends SavingAccountDomainModel {

	public RegularAccountDomainModel() {
	}

	@Override
	public Map<Integer, AnnualValue> getAnnualValues(int numberOfYears) {

		Map<Integer, AnnualValue> result = new HashMap<>();

		int year = LocalDate.now().getYear();

		for(int i = year; i < year + numberOfYears + 1; ++i) {
			AnnualValue annualValue = AnnualValue.builder().value(getInitialValue()).currency(getCurrency()).build();

			result.put(year, annualValue);
		}

		return result;
	}
}