package com.finki.websavings.domain.model.goal;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.util.Map;

/**
 * Real estate goal domain model.
 */
public class RealEstateGoalDomainModel extends GoalDomainModel {

	public RealEstateGoalDomainModel() {
	}

	@Override
	public Map<Integer, AnnualValue> getAnnualValues(Integer numberOfYears) {
		return null;
	}
}