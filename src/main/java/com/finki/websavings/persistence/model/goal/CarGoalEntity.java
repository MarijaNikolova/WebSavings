package com.finki.websavings.persistence.model.goal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("CarGoal")
public class CarGoalEntity extends GoalEntity implements Serializable {

	public CarGoalEntity() {
	}
}
