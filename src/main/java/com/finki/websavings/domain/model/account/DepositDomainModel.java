package com.finki.websavings.domain.model.account;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.util.Map;

/**
 * Deposit domain model.
 */
public class DepositDomainModel extends SavingAccountDomainModel {

	@Override
	public Map<Integer, AnnualValue> getAnnualValues(int numberOfYears) {
		return null;
	}
}