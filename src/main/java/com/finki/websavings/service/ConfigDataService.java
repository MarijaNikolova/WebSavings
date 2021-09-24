package com.finki.websavings.service;

import com.finki.websavings.mapper.ConfigDataPersistenceMapper;
import com.finki.websavings.model.ConfigData;
import com.finki.websavings.persistence.model.ConfigDataEntity;
import com.finki.websavings.persistence.repository.ConfigDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Business service for the Config data.
 */
@Component
@AllArgsConstructor
public class ConfigDataService {

  private final ConfigDataRepository configDataRepository;
  private final ConfigDataPersistenceMapper configDataPersistenceMapper;

  /**
   * Returns the given config data for the customer id.
   *
   * @param customerId the customer id.
   * @return the config data.
   */
  public ConfigData getConfigDataForCustomer(Integer customerId) {

    ConfigDataEntity configData = configDataRepository.findByCustomerId(customerId);

    return configDataPersistenceMapper.mapToModel(configData);
  }

  /**
   * Saves the config data for the given customer.
   *
   * @param customerId the customer id.
   * @param configData the config data.
   */
  public Integer saveConfigDataForCustomer(Integer customerId, ConfigData configData) {

    ConfigDataEntity configDataEntity = configDataPersistenceMapper.mapToEntity(configData, customerId);

    ConfigDataEntity savedObject = configDataRepository.save(configDataEntity);

    return savedObject.getId();
  }
}