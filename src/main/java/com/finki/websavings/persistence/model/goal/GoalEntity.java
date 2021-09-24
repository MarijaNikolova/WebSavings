package com.finki.websavings.persistence.model.goal;

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
import javax.persistence.ForeignKey;
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
@Table(name="GOAL")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("goal")
public abstract class GoalEntity implements Serializable {
	public GoalEntity() {
	}
	
	@Column(name="id", nullable=false, length=10)
	@Id	
	@GeneratedValue(generator="GOAL_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GOAL_ID_GENERATOR", strategy="native")	
	private Integer id;
	
	@ManyToOne(targetEntity= CustomerEntity.class, fetch=FetchType.LAZY)
	@org.hibernate.annotations.Cascade({CascadeType.LOCK})
	@JoinColumns(value={ @JoinColumn(name="customerID", referencedColumnName="id", nullable=false) }, foreignKey=@ForeignKey(name="FKGoal327906"))
	private CustomerEntity customer;
	
	@Column(name="description", nullable=false, length=255)
	private String description;
	
	@Column(name="plannedForDate", nullable=false, length=255)
	private String plannedForDate;
	
	@Column(name="value", nullable=false)
	private double value;
	
	@Column(name="currency", nullable=true, length=255)
	private String currency;
}
