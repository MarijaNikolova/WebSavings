package com.finki.websavings.persistence.mapper;

import com.finki.websavings.model.ConfigData;
import com.finki.websavings.persistence.model.config.ConfigDataEntity;
import com.finki.websavings.persistence.model.customer.CustomerEntity;
import org.springframework.stereotype.Component;

/**
 * Maps the {@link ConfigData} to {@link ConfigDataEntity} and vice versa.
 */
@Component
public class ConfigDataPersistenceMapper {

  /**
   * Maps the model to the corresponding entity.
   *
   * @param configData the config data.
   * @return the entity.
   */
  public ConfigDataEntity mapToEntity(ConfigData configData, Integer customerId) {

    ConfigDataEntity configDataEntity = new ConfigDataEntity();
    configDataEntity.setLanguage(configData.getLanguage());
    configDataEntity.setNumberOfYears(configData.getNumberOfYears());

    CustomerEntity customer = new CustomerEntity();
    customer.setId(customerId);
    configDataEntity.setCustomer(customer);

    return configDataEntity;
  }

  /**
   * Maps the entity data to the model.
   *
   * @param configDataEntity the entity.
   * @return the model.
   */
  public ConfigData mapToModel(ConfigDataEntity configDataEntity) {

    ConfigData configData = new ConfigData();
    configData.setNumberOfYears(configDataEntity.getNumberOfYears());
    configData.setLanguage(configData.getLanguage());
    return configData;
  }
}