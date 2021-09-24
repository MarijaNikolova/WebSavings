package com.finki.websavings.domain.model.account;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.util.Map;

/**
 * Regular account domain model.
 */
public class RegularAccountDomainModel extends SavingAccountDomainModel {

	public RegularAccountDomainModel() {
	}

	@Override
	public Map<Integer, AnnualValue> getAnnualValues(int numberOfYears) {
		return null;
	}
}