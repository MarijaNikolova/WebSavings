package com.finki.websavings.persistence.repository;

import com.finki.websavings.persistence.model.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa repository for the Customer data.
 */
@Repository
public interface CustomerDataRepository extends JpaRepository<CustomerEntity, Integer> {

  /**
   * Retrieves the customer for the given email.
   *
   * @param email the email.
   * @param password the password.
   * @return the customer entity
   */
  CustomerEntity findByEmailAndPassword(String email, String password);
}
