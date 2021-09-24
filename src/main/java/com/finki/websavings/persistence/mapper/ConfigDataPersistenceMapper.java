package com.finki.websavings.persistence.mapper;

import com.finki.websavings.domain.model.configdata.ConfigDataDomainModel;
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
  public ConfigDataEntity mapToEntity(ConfigDataDomainModel configData, Integer customerId) {

    ConfigDataEntity configDataEntity = new ConfigDataEntity();
    configDataEntity.setLanguage(configData.getLanguage());
    configDataEntity.setNumberOfYears(configData.getNumberOfYears());
    configDataEntity.setId(configData.getId());

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
  public ConfigDataDomainModel mapToModel(ConfigDataEntity configDataEntity) {

    ConfigDataDomainModel configData = new ConfigDataDomainModel();
    configData.setNumberOfYears(configDataEntity.getNumberOfYears());
    configData.setLanguage(configData.getLanguage());
    configData.setId(configDataEntity.getId());

    return configData;
  }
}