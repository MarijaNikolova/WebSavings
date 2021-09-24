package com.finki.websavings.persistence.repository;

import com.finki.websavings.persistence.model.config.ConfigDataEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Crud repository for the {@link ConfigDataEntity}.
 */
@Repository
public interface ConfigDataRepository extends JpaRepository<ConfigDataEntity, Integer> {

  /**
   * Returns the {@link ConfigDataEntity} for the given customer id.
   *
   * @param customerId the customer id.
   * @return the config data entity.
   */
  ConfigDataEntity findByCustomerId(Integer customerId);
}