package com.finki.websavings.domain.model.goal;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.util.Map;

/**
 * Domain model for the other goals.
 */
public class OtherGoalDomainModel extends GoalDomainModel {

	public OtherGoalDomainModel() {
	}

	@Override
	public Map<Integer, AnnualValue> getAnnualValues(Integer numberOfYears) {
		return null;
	}
}