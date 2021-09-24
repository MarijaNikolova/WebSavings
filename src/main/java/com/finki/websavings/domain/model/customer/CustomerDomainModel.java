package com.finki.websavings.domain.model.customer;

import lombok.Getter;
import lombok.Setter;

/**
 * Domain model for the customer.
 */
@Getter
@Setter
public class CustomerDomainModel {

  private Integer id;
  private String surname;
  private String name;
  private String email;
  private String dateOfBirth;
  private String password;
}
