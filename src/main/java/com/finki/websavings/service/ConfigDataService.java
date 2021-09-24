package com.finki.websavings.service;

import com.finki.websavings.domain.mapper.ConfigDataDomainMapper;
import com.finki.websavings.domain.model.configdata.ConfigDataDomainModel;
import com.finki.websavings.persistence.mapper.ConfigDataPersistenceMapper;
import com.finki.websavings.model.ConfigData;
import com.finki.websavings.persistence.model.config.ConfigDataEntity;
import com.finki.websavings.persistence.repository.config.ConfigDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Business service for the Config data.
 */
@Component
@AllArgsConstructor
public class ConfigDataService {

  private final ConfigDataRepository configDataRepository;
  private final ConfigDataDomainMapper configDataDomainMapper;
  private final ConfigDataPersistenceMapper configDataPersistenceMapper;

  /**
   * Returns the given config data for the customer id.
   *
   * @param customerId the customer id.
   * @return the config data.
   */
  public ConfigData getConfigDataForCustomer(Integer customerId) {

    ConfigDataEntity configData = configDataRepository.findByCustomerId(customerId);

    ConfigDataDomainModel configDataDomainModel = configDataPersistenceMapper.mapToModel(configData);

    return configDataDomainMapper.toDto(configDataDomainModel);
  }

  /**
   * Saves the config data for the given customer.
   *
   * @param customerId the customer id.
   * @param configData the config data.
   */
  public Integer saveConfigDataForCustomer(Integer customerId, ConfigData configData) {

    ConfigDataDomainModel configDataDomainModel = configDataDomainMapper.toDomainModel(configData);

    ConfigDataEntity configDataEntity = configDataPersistenceMapper.mapToEntity(configDataDomainModel, customerId);

    ConfigDataEntity savedObject = configDataRepository.save(configDataEntity);

    return savedObject.getId();
  }
}