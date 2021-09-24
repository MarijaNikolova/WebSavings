package com.finki.websavings.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The entity for the Config data model.
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="CONFIG_DATA")
public class ConfigDataEntity implements Serializable {
	public ConfigDataEntity() {
	}
	
	@Column(name="id", nullable=false, length=10)
	@Id	
	@GeneratedValue(generator="CONFIGDATA_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CONFIGDATA_ID_GENERATOR", strategy="native")	
	private Integer id;
	
	@Column(name="numberOfYears", nullable=true, length=10)
	private Integer numberOfYears;
	
	@Column(name="language", nullable=true, length=255)
	private String language;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customerId", referencedColumnName = "id")
	private CustomerEntity customer;

	public void setNumberOfYears(Integer value) {
		this.numberOfYears = value;
	}
	
	public Integer getNumberOfYears() {
		return numberOfYears;
	}
	
	public void setLanguage(String value) {
		this.language = value;
	}
	
	public String getLanguage() {
		return language;
	}
	
	private void setId(int value) {
		this.id = value;
	}

	public Integer getId() {
		return id;
	}

	public void setID(Integer ID) {
		this.id = ID;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
}