package com.finki.websavings.persistence.repository.goal;

import com.finki.websavings.persistence.model.goal.GoalEntity;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repository for the goals.
 */
@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Integer> {

  /**
   * Retrieves the goals for the given customer.
   *
   * @param customerId the customer id.
   * @return the goals.
   */
  List<GoalEntity> findByCustomerId(Integer customerId);
}
