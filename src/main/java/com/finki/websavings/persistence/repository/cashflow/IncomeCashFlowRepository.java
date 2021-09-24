package com.finki.websavings.persistence.repository.cashflow;

import com.finki.websavings.persistence.model.cashflow.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repository for the Income Cash flow.
 */
@Repository
public interface IncomeCashFlowRepository extends JpaRepository<IncomeEntity, Integer> {

  /**
   * Finds all income entities for the given customer id.
   * @return all income entities for the customer id.
   */
  List<IncomeEntity> findAllByCustomerId(Integer customerId);
}
