package com.finki.websavings.persistence.model.cashflow;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("expense")
public class ExpenseEntity extends CashFlowEntity implements Serializable {

    public ExpenseEntity() {
    }
}