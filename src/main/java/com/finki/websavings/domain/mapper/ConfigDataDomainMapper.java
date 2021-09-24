package com.finki.websavings.domain.mapper;

import com.finki.websavings.domain.model.configdata.ConfigDataDomainModel;
import com.finki.websavings.model.ConfigData;
import org.springframework.stereotype.Component;

/**
 * Mapper for the config data from the domain model to the dto and vice versa.
 */
@Component
public class ConfigDataDomainMapper {

  /**
   * Maps the given dto to domain model.
   *
   * @param configData the config data.
   * @return the domain model.
   */
  public ConfigDataDomainModel toDomainModel(ConfigData configData) {

    ConfigDataDomainModel configDataDomainModel = new ConfigDataDomainModel();
    configDataDomainModel.setLanguage(configData.getLanguage());
    configDataDomainModel.setNumberOfYears(configData.getNumberOfYears());
    configDataDomainModel.setId(configData.getId());

    return configDataDomainModel;
  }

  /**
   * Maps the domain model to the dto object.
   *
   * @param domainModel the domain model.
   * @return the dto.
   */
  public ConfigData toDto(ConfigDataDomainModel domainModel) {

    ConfigData configData = new ConfigData();
    configData.setLanguage(domainModel.getLanguage());
    configData.setId(domainModel.getId());
    configData.setNumberOfYears(domainModel.getNumberOfYears());

    return configData;
  }
}
