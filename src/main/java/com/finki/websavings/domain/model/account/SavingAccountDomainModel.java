package com.finki.websavings.domain.model.account;

import com.finki.websavings.domain.model.annualvalue.AnnualValue;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Generated with VisualParadigm
 */
@Getter
@Setter
public abstract class SavingAccountDomainModel {

	private double initialValue;
	private String currency;
	private String description;
	private SavingInstitutionDomainModel savingInstitutionDomainModel;
	private double growthRate;

	/**
   * Returns the annual values for the given number of years.
	 *
	 * @param numberOfYears the number of years.
	 * @return the annual values.
	 */
	public abstract Map<Integer, AnnualValue> getAnnualValues(int numberOfYears);
}