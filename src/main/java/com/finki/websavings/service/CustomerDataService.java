package com.finki.websavings.service;

import com.finki.websavings.domain.mapper.CustomerDomainMapper;
import com.finki.websavings.domain.model.customer.CustomerDomainModel;
import com.finki.websavings.persistence.mapper.CustomerDataPersistenceMapper;
import com.finki.websavings.model.CustomerData;
import com.finki.websavings.persistence.model.customer.CustomerEntity;
import com.finki.websavings.persistence.repository.customer.CustomerDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Service for the Customer data.
 */
@AllArgsConstructor
@Component
public class CustomerDataService {

  private final CustomerDataRepository customerDataRepository;
  private final CustomerDomainMapper customerDomainMapper;
  private final CustomerDataPersistenceMapper customerDataPersistenceMapper;

  /**
   * Returns the customer data for the given email.
   * @param email the email.
   * @param password the password.
   * @return the customer data.
   */
  public CustomerData getCustomerData(String email, String password) {

    CustomerEntity customerEntity = customerDataRepository.findByEmailAndPassword(email, password);

    CustomerDomainModel customerDomainModel = customerDataPersistenceMapper.mapToModel(customerEntity);

    return customerDomainMapper.toDto(customerDomainModel);
  }

  /**
   * Saves the customer data in the repository.
   *
   * @param customerData the customer data.
   */
  public void saveCustomerData(CustomerData customerData) {

    CustomerDomainModel customerDomainModel = customerDomainMapper.toDomainModel(customerData);

    CustomerEntity customerEntity = customerDataPersistenceMapper.mapToEntity(customerDomainModel);

    CustomerEntity byEmail = customerDataRepository.findByEmail(customerData.getEmail());
    if (byEmail != null) {
      customerEntity.setId(byEmail.getId());
      if (customerData.getPassword() == null) {
        customerEntity.setPassword(byEmail.getPassword());
      }
    }

    customerDataRepository.save(customerEntity);
  }
}
