package com.finki.websavings.persistence.model.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;

@Getter
@Setter
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("InvestmentAccount")
public class InvestmentAccountEntity extends SavingAccountEntity implements Serializable {

  @Column(name="riskFactor", nullable=true)
  private double riskFactor;
}
