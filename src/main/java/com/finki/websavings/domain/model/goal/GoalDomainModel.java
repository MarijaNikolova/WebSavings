package com.finki.websavings.domain.model.goal;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public abstract class GoalDomainModel {

	private String description;
	private String plannedForDate;
	private double value;
	private String currency;
	private Integer id;

	public abstract Map<Integer, AnnualValue> getAnnualValues(Integer numberOfYears);

}