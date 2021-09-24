package com.finki.websavings.controller;

import com.finki.websavings.model.ConfigData;
import com.finki.websavings.service.ConfigDataApi;
import com.finki.websavings.service.ConfigDataService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Controller implementation of the {@link ConfigDataApi}.
 */
@AllArgsConstructor
@RestController
public class ConfigDataController implements ConfigDataApi {

  private final ConfigDataService configDataService;

  @Override
  public ResponseEntity<ConfigData> getConfigData(Integer customerId) {
    ConfigData configData = configDataService.getConfigDataForCustomer(customerId);
    return ResponseEntity.of(Optional.of(configData));
  }

  @Override
  public ResponseEntity<Void> saveConfigData(Integer customerId, ConfigData body) {

    configDataService.saveConfigDataForCustomer(customerId, body);

    return null;
  }
}