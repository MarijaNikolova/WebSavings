package com.finki.websavings.persistence.mapper;

import com.finki.websavings.domain.model.customer.CustomerDomainModel;
import com.finki.websavings.model.CustomerData;
import com.finki.websavings.persistence.model.customer.CustomerEntity;
import com.finki.websavings.util.DateUtility;
import org.springframework.stereotype.Component;

/**
 * Mapper from persistence to model and vice versa for the customer data.
 */
@Component
public class CustomerDataPersistenceMapper {

  /**
   * Maps from model to entity.
   *
   * @param customerData the customer data.
   * @return the entity.
   */
  public CustomerEntity mapToEntity(CustomerDomainModel customerData) {

    CustomerEntity customerEntity = new CustomerEntity();

    customerEntity.setPassword(customerData.getPassword());
    customerEntity.setEmail(customerData.getEmail());
    customerEntity.setDateOfBirth(customerData.getDateOfBirth());
    customerEntity.setName(customerData.getName());
    customerEntity.setSurname(customerData.getSurname());
    customerEntity.setId(customerData.getId());

    return customerEntity;
  }

  /**
   * Maps the entity to the model.
   *
   * @param entity the entity.
   * @return the model.
   */
  public CustomerDomainModel mapToModel(CustomerEntity entity) {

    CustomerDomainModel customerData = new CustomerDomainModel();
    customerData.setEmail(entity.getEmail());
    customerData.setDateOfBirth(entity.getDateOfBirth());
    customerData.setName(entity.getName());
    customerData.setSurname(entity.getSurname());
    customerData.setId(entity.getId());

    return customerData;
  }
}
