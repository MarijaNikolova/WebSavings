package com.finki.websavings.persistence.model.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="SAVING_INSTITUTION")
public class SavingInstitutionEntity implements Serializable {

	@Column(name="id", nullable=false, length=10)
	@Id	
	@GeneratedValue(generator="SAVINGINSTITUTION_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="SAVINGINSTITUTION_ID_GENERATOR", strategy="native")	
	private Integer id;
	
	@Column(name="name", nullable=true, length=255)
	private String name;
	
	@Column(name="type", nullable=true, length=255)
	private String type;
	
}
