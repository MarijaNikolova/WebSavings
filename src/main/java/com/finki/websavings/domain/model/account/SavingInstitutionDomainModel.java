package com.finki.websavings.domain.model.account;

import lombok.Getter;
import lombok.Setter;

/**
 * Saving institution.
 */
@Getter
@Setter
public class SavingInstitutionDomainModel {

	private String name;
	private String type;
	private Integer id;
}