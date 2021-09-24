package com.finki.websavings.persistence.mapper;

import com.finki.websavings.domain.model.goal.CarGoalDomainModel;
import com.finki.websavings.domain.model.goal.GoalDomainModel;
import com.finki.websavings.domain.model.goal.OtherGoalDomainModel;
import com.finki.websavings.domain.model.goal.RealEstateGoalDomainModel;
import com.finki.websavings.domain.model.goal.TravelGoalDomainModel;
import com.finki.websavings.persistence.model.customer.CustomerEntity;
import com.finki.websavings.persistence.model.goal.CarGoalEntity;
import com.finki.websavings.persistence.model.goal.GoalEntity;
import com.finki.websavings.persistence.model.goal.OtherGoalEntity;
import com.finki.websavings.persistence.model.goal.RealEstateGoalEntity;
import com.finki.websavings.persistence.model.goal.TravelGoalEntity;
import org.springframework.stereotype.Component;

/**
 * Persistence mapper for the goals.
 */
@Component
public class GoalPersistenceMapper {

  /**
   * Maps the entity to the domain model.
   *
   * @param goalEntity the entity.
   * @return the domain model.
   */
  public GoalDomainModel mapToDomainModel(GoalEntity goalEntity) {

    GoalDomainModel goalDomainModel = createInstanceForDomain(goalEntity);
    goalDomainModel.setCurrency(goalEntity.getCurrency());
    goalDomainModel.setValue(goalEntity.getValue());
    goalDomainModel.setPlannedForDate(goalEntity.getPlannedForDate());
    goalDomainModel.setDescription(goalEntity.getDescription());
    goalDomainModel.setId(goalEntity.getId());

    return goalDomainModel;
  }

  private GoalDomainModel createInstanceForDomain(GoalEntity goalEntity) {

    if (goalEntity instanceof TravelGoalEntity) {
      return new TravelGoalDomainModel();
    } else if (goalEntity instanceof OtherGoalEntity) {
      return new OtherGoalDomainModel();
    } else if (goalEntity instanceof RealEstateGoalEntity) {
      return new RealEstateGoalDomainModel();
    } else if (goalEntity instanceof CarGoalEntity) {
      return new CarGoalDomainModel();
    }

    throw new RuntimeException("not supported goal type");
  }

  /**
   * Maps the domain model to the entity.
   *
   * @param goalDomainModel the goal domain model.
   * @return the entity.
   */
  public GoalEntity mapToEntity(GoalDomainModel goalDomainModel, Integer customerId) {

    GoalEntity goalEntity = createInstanceForEntity(goalDomainModel);

    goalEntity.setCurrency(goalDomainModel.getCurrency());
    goalEntity.setValue(goalDomainModel.getValue());
    goalEntity.setPlannedForDate(goalDomainModel.getPlannedForDate());
    goalEntity.setDescription(goalDomainModel.getDescription());
    goalEntity.setId(goalDomainModel.getId());

    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(customerId);
    goalEntity.setCustomer(customerEntity);

    return goalEntity;
  }

  private GoalEntity createInstanceForEntity(GoalDomainModel goalDomainModel) {

    if (goalDomainModel instanceof TravelGoalDomainModel) {
      return new TravelGoalEntity();
    } else if (goalDomainModel instanceof OtherGoalDomainModel) {
      return new OtherGoalEntity();
    } else if (goalDomainModel instanceof RealEstateGoalDomainModel) {
      return new RealEstateGoalEntity();
    } else if (goalDomainModel instanceof CarGoalDomainModel) {
      return new CarGoalEntity();
    }

    throw new RuntimeException("not supported goal type");
  }
}
