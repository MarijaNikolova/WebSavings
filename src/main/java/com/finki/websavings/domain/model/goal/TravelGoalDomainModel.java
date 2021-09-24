package com.finki.websavings.domain.model.goal;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.util.Map;

/**
 * Travel goal domain model.
 */
public class TravelGoalDomainModel extends GoalDomainModel {

	public TravelGoalDomainModel() {
	}

	@Override
	public Map<Integer, AnnualValue> getAnnualValues(Integer numberOfYears) {
		return null;
	}
}