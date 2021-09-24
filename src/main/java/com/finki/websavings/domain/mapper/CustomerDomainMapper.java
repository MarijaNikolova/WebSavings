package com.finki.websavings.domain.mapper;

import com.finki.websavings.domain.model.customer.CustomerDomainModel;
import com.finki.websavings.model.CustomerData;
import com.finki.websavings.util.DateUtility;
import org.springframework.stereotype.Component;

/**
 * Mapper for the customer domain model which maps from to dtos and vice versa.
 */
@Component
public class CustomerDomainMapper {

  /**
   * Maps the dto to the domain model.
   *
   * @param customer the customer dto.
   * @return the domain model.
   */
  public CustomerDomainModel toDomainModel(CustomerData customer) {

    CustomerDomainModel customerDomainModel = new CustomerDomainModel();
    customerDomainModel.setId(customerDomainModel.getId());
    customerDomainModel.setEmail(customer.getEmail());
    customerDomainModel.setDateOfBirth(DateUtility.convertDateToIsoStringFormat(customer.getDateOfBirth()));
    customerDomainModel.setPassword(customer.getPassword());
    customerDomainModel.setName(customer.getName());
    customerDomainModel.setSurname(customer.getSurname());

    return customerDomainModel;
  }

  /**
   * Maps the domain model to the dto.
   *
   * @param customerDomainModel the domain model.
   * @return the dto.
   */
  public CustomerData toDto(CustomerDomainModel customerDomainModel) {

    CustomerData customerData = new CustomerData();

    customerData.setId(customerDomainModel.getId());
    customerData.setEmail(customerDomainModel.getEmail());
    customerData.setDateOfBirth(DateUtility.fromIsoDateString(customerDomainModel.getDateOfBirth()));
    customerData.setPassword(customerDomainModel.getPassword());
    customerData.setName(customerDomainModel.getName());
    customerData.setSurname(customerDomainModel.getSurname());

    return customerData;
  }
}
