package com.finki.websavings.persistence.repository;

import com.finki.websavings.persistence.model.cashflow.ExpenseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repository for the Expense Cash flow.
 */
@Repository
public interface ExpenseCashFlowRepository extends JpaRepository<ExpenseEntity, Integer> {

  /**
   * Finds all expense entities for the given customer id.
   * @return all expense entities for the customer id.
   */
  List<ExpenseEntity> findAllByCustomerId(Integer customerId);
}