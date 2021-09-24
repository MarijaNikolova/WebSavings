package com.finki.websavings.controller;

import com.finki.websavings.model.CustomerData;
import com.finki.websavings.service.CustomerApi;
import com.finki.websavings.service.CustomerDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Implementation of the {@link CustomerApi}.
 */
@AllArgsConstructor
@RestController
public class CustomerController implements CustomerApi {

  private final CustomerDataService customerDataService;

  @Override
  public ResponseEntity<CustomerData> getCustomerData(String customerEmail, String password) {

    CustomerData customerData = customerDataService.getCustomerData(customerEmail, password);

    return ResponseEntity.of(Optional.of(customerData));
  }

  @Override
  public ResponseEntity<Void> saveCustomerData(CustomerData body) {
    customerDataService.saveCustomerData(body);
    return null;
  }
}
