package com.finki.websavings.persistence.mapper;

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
  public CustomerEntity mapToEntity(CustomerData customerData) {

    CustomerEntity customerEntity = new CustomerEntity();

    customerEntity.setPassword(customerData.getPassword());
    customerEntity.setEmail(customerData.getEmail());
    customerEntity.setDateOfBirth(DateUtility.convertDateToIsoStringFormat(customerData.getDateOfBirth()));
    customerEntity.setName(customerData.getName());
    customerEntity.setSurname(customerData.getSurname());

    return customerEntity;
  }

  /**
   * Maps the entity to the model.
   *
   * @param entity the entity.
   * @return the model.
   */
  public CustomerData mapToModel(CustomerEntity entity) {

    CustomerData customerData = new CustomerData();
    customerData.setEmail(entity.getEmail());
    customerData.setDateOfBirth(DateUtility.fromIsoDateString(entity.getDateOfBirth()));
    customerData.setName(entity.getName());
    customerData.setSurname(entity.getSurname());
    customerData.setId(entity.getId());

    return customerData;
  }
}
