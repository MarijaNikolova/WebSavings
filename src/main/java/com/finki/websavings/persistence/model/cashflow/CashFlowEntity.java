package com.finki.websavings.persistence.model.cashflow;

import com.finki.websavings.persistence.model.customer.CustomerEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="CASHFLOW")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("CashFlow")
public abstract class CashFlowEntity implements Serializable {

	public CashFlowEntity() {
	}
	
	@Column(name="id", nullable=false, length=10)
	@Id	
	@GeneratedValue(generator="CASHFLOW_ID_GENERATOR")
	@org.hibernate.annotations.GenericGenerator(name="CASHFLOW_ID_GENERATOR", strategy="native")	
	private Integer id;
	
	@ManyToOne(targetEntity= CustomerEntity.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({CascadeType.LOCK})
	@JoinColumns(value={ @JoinColumn(name="customerId", referencedColumnName="id", nullable=false) })
	private CustomerEntity customer;
	
	@Column(name="category", nullable=true, length=255)
	private String category;
	
	@Column(name="value", nullable=false)
	private double value;
	
	@Column(name="currency", nullable=true, length=255)
	private String currency;
	
	@Column(name="occurrenceType", nullable=true, length=255)
	private String occurrenceType;
	
	@Column(name="description", nullable=true, length=255)
	private String description;

	@Column(name="dateFrom")
	private String dateFrom;

	@Column(name="dateTo")
	private String dateTo;

}