package com.finki.websavings.domain.model.goal;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;

import java.util.Map;

/**
 * Domain model for the car goal.
 */
public class CarGoalDomainModel extends GoalDomainModel {

	public CarGoalDomainModel() {
	}

	@Override
	public Map<Integer, AnnualValue> getAnnualValues(Integer numberOfYears) {
		return null;
	}

}