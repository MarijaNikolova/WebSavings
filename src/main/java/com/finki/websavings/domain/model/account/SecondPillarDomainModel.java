package com.finki.websavings.domain.model.account;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.util.Map;

/**
 * Second pillar domain model.
 */
public class SecondPillarDomainModel extends SavingAccountDomainModel {

	@Override
	public Map<Integer, AnnualValue> getAnnualValues(int numberOfYears) {
		return null;
	}
}