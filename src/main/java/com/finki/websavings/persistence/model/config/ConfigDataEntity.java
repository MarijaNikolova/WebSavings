package com.finki.websavings.persistence.model.config;

import com.finki.websavings.persistence.model.customer.CustomerEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;

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
@Getter
@Setter
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
	
	@OneToOne
	@org.hibernate.annotations.Cascade({CascadeType.LOCK})
	@JoinColumn(name = "customerId", referencedColumnName = "id")
	private CustomerEntity customer;
}