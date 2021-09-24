package com.finki.websavings.persistence.mapper;

import com.finki.websavings.domain.model.account.DepositDomainModel;
import com.finki.websavings.domain.model.account.InvestmentAccountDomainModel;
import com.finki.websavings.domain.model.account.RegularAccountDomainModel;
import com.finki.websavings.domain.model.account.SavingAccountDomainModel;
import com.finki.websavings.domain.model.account.SavingInstitutionDomainModel;
import com.finki.websavings.domain.model.account.SecondPillarDomainModel;
import com.finki.websavings.persistence.model.account.DepositEntity;
import com.finki.websavings.persistence.model.account.InvestmentAccountEntity;
import com.finki.websavings.persistence.model.account.RegularAccountEntity;
import com.finki.websavings.persistence.model.account.SavingAccountEntity;
import com.finki.websavings.persistence.model.account.SavingInstitutionEntity;
import com.finki.websavings.persistence.model.account.SecondPillarEntity;

import com.finki.websavings.persistence.model.customer.CustomerEntity;
import org.springframework.stereotype.Component;

/**
 * Persistence mapper for the accounts.
 */
@Component
public class AccountPersistenceMapper {

  /**
   * Maps from entity to domain model.
   *
   * @param savingAccountEntity the entity.
   * @return the domain model.
   */
  public SavingAccountDomainModel toDomainModel(SavingAccountEntity savingAccountEntity) {

    SavingAccountDomainModel savingAccountDomainModel = getInstance(savingAccountEntity);

    savingAccountDomainModel.setCurrency(savingAccountEntity.getCurrency());
    savingAccountDomainModel.setInitialValue(savingAccountEntity.getInitialValue());
    savingAccountDomainModel.setGrowthRate(savingAccountEntity.getGrowthRate());
    savingAccountDomainModel.setDescription(savingAccountEntity.getDescription());

    SavingInstitutionDomainModel savingInstitutionDomainModel = new SavingInstitutionDomainModel();
    SavingInstitutionEntity bank = savingAccountEntity.getBank();
    savingInstitutionDomainModel.setName(bank.getName());
    savingInstitutionDomainModel.setType(bank.getType());
    savingAccountDomainModel.setSavingInstitutionDomainModel(savingInstitutionDomainModel);

    if(savingAccountEntity instanceof InvestmentAccountEntity) {
      double riskFactor = ((InvestmentAccountEntity) savingAccountEntity).getRiskFactor();
      ((InvestmentAccountDomainModel) savingAccountDomainModel).setRiskFactor(riskFactor);
    }

    return savingAccountDomainModel;
  }

  private SavingAccountDomainModel getInstance(SavingAccountEntity savingAccountEntity) {

    if (savingAccountEntity instanceof DepositEntity) {
      return new DepositDomainModel();
    } else if (savingAccountEntity instanceof RegularAccountEntity){
      return new RegularAccountDomainModel();
    } else if (savingAccountEntity instanceof SecondPillarEntity) {
      return new SecondPillarDomainModel();
    } else if (savingAccountEntity instanceof InvestmentAccountEntity) {
      return new InvestmentAccountDomainModel();
    }

    throw new RuntimeException("not supported type");
  }

  /**
   * Maps the domain model to the given entity.
   *
   * @param savingAccountDomainModel the domain model.
   * @return the entity.
   */
  public SavingAccountEntity toEntity(
    SavingAccountDomainModel savingAccountDomainModel, Integer customerId, SavingInstitutionEntity bank) {

    SavingAccountEntity savingAccountEntity = getInstance(savingAccountDomainModel);

    savingAccountEntity.setCurrency(savingAccountDomainModel.getCurrency());
    savingAccountEntity.setInitialValue(savingAccountDomainModel.getInitialValue());
    savingAccountEntity.setGrowthRate(savingAccountDomainModel.getGrowthRate());
    savingAccountEntity.setDescription(savingAccountDomainModel.getDescription());

    savingAccountEntity.setBank(bank);

    if(savingAccountDomainModel instanceof InvestmentAccountDomainModel) {
      double riskFactor = ((InvestmentAccountDomainModel) savingAccountDomainModel).getRiskFactor();
      ((InvestmentAccountEntity) savingAccountEntity).setRiskFactor(riskFactor);
    }

    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(customerId);
    savingAccountEntity.setCustomer(customerEntity);

    return savingAccountEntity;
  }

  private SavingAccountEntity getInstance(SavingAccountDomainModel savingAccountDomainModel) {

    if (savingAccountDomainModel instanceof DepositDomainModel) {
      return new DepositEntity();
    } else if (savingAccountDomainModel instanceof RegularAccountDomainModel){
      return new RegularAccountEntity();
    } else if (savingAccountDomainModel instanceof SecondPillarDomainModel) {
      return new SecondPillarEntity();
    } else if (savingAccountDomainModel instanceof InvestmentAccountDomainModel) {
      return new InvestmentAccountEntity();
    }

    throw new RuntimeException("not supported type");
  }
}
