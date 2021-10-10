package com.finki.websavings.domain.mapper;

import com.finki.websavings.domain.model.goal.CarGoalDomainModel;
import com.finki.websavings.domain.model.goal.GoalDomainModel;
import com.finki.websavings.domain.model.goal.OtherGoalDomainModel;
import com.finki.websavings.domain.model.goal.RealEstateGoalDomainModel;
import com.finki.websavings.domain.model.goal.TravelGoalDomainModel;
import com.finki.websavings.model.Goal;
import com.finki.websavings.util.DateUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Domain mapper for the goals to dtos and vice versa.
 */
@AllArgsConstructor
@Component
public class GoalDomainMapper {

  /**
   * Maps the dto to the domain model.
   * @param goal the dto.
   * @return the domain model.
   */
  public GoalDomainModel toDomainModel(Goal goal) {

    GoalDomainModel goalDomainModel = getInstance(goal);

    goalDomainModel.setDescription(goal.getDescription());
    goalDomainModel.setValue(goal.getValue().doubleValue());
    goalDomainModel.setPlannedForDate(DateUtility.convertDateToIsoStringFormat(goal.getPlannedFor()));
    goalDomainModel.setCurrency(goal.getCurrency());
    goalDomainModel.setId(goal.getId());

    return goalDomainModel;
  }

  private GoalDomainModel getInstance(Goal goal) {

    if (goal.getType() == Goal.TypeEnum.CAR_GOAL) {
      return new CarGoalDomainModel();
    } else if (goal.getType() == Goal.TypeEnum.OTHER_GOAL) {
      return new OtherGoalDomainModel();
    } else if (goal.getType() == Goal.TypeEnum.TRAVEL_GOAL) {
      return new TravelGoalDomainModel();
    } else  if (goal.getType() == Goal.TypeEnum.REAL_ESTATE_GOAL) {
      return new RealEstateGoalDomainModel();
    }

    throw new RuntimeException("unsupported type");
  }

  /**
   * Maps the domain model to the dto.
   *
   * @param goalDomainModel the domain model.
   * @return goal dto.
   */
  public Goal mapToDto(GoalDomainModel goalDomainModel) {

    Goal goal = new Goal();

    goal.setDescription(goalDomainModel.getDescription());
    goal.setValue(BigDecimal.valueOf(goalDomainModel.getValue()));
    goal.setPlannedFor(DateUtility.fromIsoDateString(goalDomainModel.getPlannedForDate()));
    goal.setCurrency(goalDomainModel.getCurrency());
    goal.setType(getType(goalDomainModel));
    goal.setId(goalDomainModel.getId());

    return goal;
  }

  private Goal.TypeEnum getType(GoalDomainModel goalDomainModel) {

    if (goalDomainModel instanceof CarGoalDomainModel) {
      return Goal.TypeEnum.CAR_GOAL;
    } else if (goalDomainModel instanceof OtherGoalDomainModel) {
      return Goal.TypeEnum.OTHER_GOAL;
    } else if (goalDomainModel instanceof TravelGoalDomainModel) {
      return Goal.TypeEnum.TRAVEL_GOAL;
    } else  if (goalDomainModel instanceof RealEstateGoalDomainModel) {
      return Goal.TypeEnum.REAL_ESTATE_GOAL;
    }

    throw new RuntimeException("unsupported type");
  }
}
