package com.finki.websavings.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="CUSTOMER")
public class CustomerEntity implements Serializable {
	public CustomerEntity() {
	}
	
	@Column(name="id", nullable=false, length=10)
	@Id	
	@GeneratedValue(generator="CUSTOMER_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CUSTOMER_ID_GENERATOR", strategy="native")	
	private Integer id;
	
	@Column(name="surname", nullable=false, length=255)
	private String surname;
	
	@Column(name="name", nullable=false, length=255)
	private String name;
	
	@Column(name="email", nullable=false, length=255, unique = true)
	private String email;
	
	@Column(name="dateOfBirth", nullable=false, length=255)
	private String dateOfBirth;

	@Column(name = "password", nullable = false, length = 255)
	private String password;
	
//	@OneToMany(mappedBy= "customer", targetEntity= SavingAccountEntity.class)
//	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
//	private java.util.Set savingAccount = new java.util.HashSet();
//
//	@OneToMany(mappedBy="customer", orphanRemoval=true, targetEntity= CashFlowEntity.class)
//	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})
//	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
//	private java.util.Set cashflow = new java.util.HashSet();
//
//	@OneToMany(mappedBy="customer", orphanRemoval=true, targetEntity= GoalEntity.class)
//	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})
//	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
//	private java.util.Set goal = new java.util.HashSet();

	public void setSurname(String value) {
		this.surname = value;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setDateOfBirth(String value) {
		this.dateOfBirth = value;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
//	public void setSavingAccount(java.util.Set value) {
//		this.savingAccount = value;
//	}
//
//	public java.util.Set getSavingAccount() {
//		return savingAccount;
//	}
//
//
//	public void setCashflow(java.util.Set value) {
//		this.cashflow = value;
//	}
//
//	public java.util.Set getCashflow() {
//		return cashflow;
//	}
//
//
//	public void setGoal(java.util.Set value) {
//		this.goal = value;
//	}
//
//	public java.util.Set getGoal() {
//		return goal;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}