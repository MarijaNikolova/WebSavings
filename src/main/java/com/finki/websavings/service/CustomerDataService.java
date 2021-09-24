package com.finki.websavings.service;

import com.finki.websavings.persistence.mapper.CustomerDataPersistenceMapper;
import com.finki.websavings.model.CustomerData;
import com.finki.websavings.persistence.model.customer.CustomerEntity;
import com.finki.websavings.persistence.repository.CustomerDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Service for the Customer data.
 */
@AllArgsConstructor
@Component
public class CustomerDataService {

  private final CustomerDataRepository customerDataRepository;
  private final CustomerDataPersistenceMapper customerDataPersistenceMapper;

  /**
   * Returns the customer data for the given email.
   * @param email the email.
   * @param password the password.
   * @return the customer data.
   */
  public CustomerData getCustomerData(String email, String password) {

    CustomerEntity customerEntity = customerDataRepository.findByEmailAndPassword(email, password);
    return customerDataPersistenceMapper.mapToModel(customerEntity);
  }

  /**
   * Saves the customer data in the repository.
   *
   * @param customerData the customer data.
   */
  public void saveCustomerData(CustomerData customerData) {

    CustomerEntity customerEntity = customerDataPersistenceMapper.mapToEntity(customerData);
    customerDataRepository.save(customerEntity);
  }
}
