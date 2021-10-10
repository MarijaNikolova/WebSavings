package com.finki.websavings.service;

import com.finki.websavings.domain.mapper.GoalDomainMapper;
import com.finki.websavings.domain.model.goal.GoalDomainModel;
import com.finki.websavings.model.Goal;
import com.finki.websavings.persistence.mapper.GoalPersistenceMapper;
import com.finki.websavings.persistence.model.customer.CustomerEntity;
import com.finki.websavings.persistence.model.goal.GoalEntity;
import com.finki.websavings.persistence.repository.customer.CustomerDataRepository;
import com.finki.websavings.persistence.repository.goal.GoalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Goals service.
 */
@AllArgsConstructor
@Component
public class GoalService {

  private final GoalDomainMapper goalDomainMapper;
  private final GoalRepository goalRepository;
  private final GoalPersistenceMapper persistenceMapper;
  private final CustomerDataRepository customerDataRepository;

  /**
   * Return all goals for the customer.
   *
   * @param customerId the customer id.
   * @return the list of goals.
   */
  public List<Goal> getGoalsForCustomer(Integer customerId) {

    List<GoalEntity> byCustomerId = goalRepository.findByCustomerId(customerId);

    List<GoalDomainModel> goalDomainModels =
      byCustomerId
      .stream()
      .map(persistenceMapper::mapToDomainModel)
      .collect(Collectors.toList());

    return
      goalDomainModels
      .stream()
      .map(goalDomainMapper::mapToDto)
      .collect(Collectors.toList());
  }

  /**
   * Saves the goals for the customer.
   *
   * @param customerId the customer id.
   * @param goal the goal.
   */
  public void saveGoal(Integer customerId, Goal goal) {

    GoalDomainModel goalDomainModel = goalDomainMapper.toDomainModel(goal);
    GoalEntity goalEntity = persistenceMapper.mapToEntity(goalDomainModel, customerId);

    Optional<CustomerEntity> customer = customerDataRepository.findById(customerId);
    goalEntity.setCustomer(customer.get());

    goalRepository.save(goalEntity);
  }

  /**
   * Deletes the goal for the given id.
   * @param goalId the goal id.
   */
  public void deleteGoal(Integer goalId) {
    goalRepository.deleteById(goalId);
  }
}
