package com.finki.websavings.persistence.mapper;


import com.finki.websavings.domain.model.account.SavingInstitutionDomainModel;
import com.finki.websavings.persistence.model.account.SavingInstitutionEntity;
import org.springframework.stereotype.Component;

/**
 * Persistence mapper for the saving institution.
 */
@Component
public class SavingInstitutionPersistenceMapper {

  /**
   * Maps from entity to domain model.
   *
   * @param savingInstitutionEntity the entity.
   * @return the domain model.
   */
  public SavingInstitutionDomainModel toDomainModel(SavingInstitutionEntity savingInstitutionEntity) {

    SavingInstitutionDomainModel savingInstitutionDomainModel = new SavingInstitutionDomainModel();

    savingInstitutionDomainModel.setName(savingInstitutionEntity.getName());
    savingInstitutionDomainModel.setType(savingInstitutionEntity.getType());
    savingInstitutionDomainModel.setId(savingInstitutionEntity.getId());

    return savingInstitutionDomainModel;
  }

  /**
   * Maps the domain model to the given entity.
   *
   * @param savingInstitutionDomainModel the domain model.
   * @return the entity.
   */
  public SavingInstitutionEntity toEntity(SavingInstitutionDomainModel savingInstitutionDomainModel) {

    SavingInstitutionEntity bank = new SavingInstitutionEntity();
    bank.setName(savingInstitutionDomainModel.getName());
    bank.setType(savingInstitutionDomainModel.getType());
    bank.setId(savingInstitutionDomainModel.getId());

    return bank;
  }
}
