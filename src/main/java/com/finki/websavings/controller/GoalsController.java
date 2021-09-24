package com.finki.websavings.controller;


import com.finki.websavings.model.Goal;
import com.finki.websavings.service.GoalService;
import com.finki.websavings.service.GoalsApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Rest controller which is implementation for the {@link GoalsApi}.
 */
@AllArgsConstructor
@RestController
public class GoalsController implements GoalsApi {

  private final GoalService goalService;

  @Override
  public ResponseEntity<Void> saveGoal(Integer customerId, Goal body) {

    goalService.saveGoal(customerId, body);

    return null;
  }

  @Override
  public ResponseEntity<List<Goal>> getGoals(Integer customerId) {

    List<Goal> goalsForCustomer = goalService.getGoalsForCustomer(customerId);

    return ResponseEntity.of(Optional.of(goalsForCustomer));
  }
}
