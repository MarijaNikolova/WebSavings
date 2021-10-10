package com.finki.websavings.domain.mapper;

import com.finki.websavings.domain.model.account.DepositDomainModel;
import com.finki.websavings.domain.model.account.InvestmentAccountDomainModel;
import com.finki.websavings.domain.model.account.RegularAccountDomainModel;
import com.finki.websavings.domain.model.account.SavingAccountDomainModel;
import com.finki.websavings.domain.model.account.SavingInstitutionDomainModel;
import com.finki.websavings.domain.model.account.SecondPillarDomainModel;
import com.finki.websavings.model.Account;
import com.finki.websavings.model.Bank;

import com.finki.websavings.persistence.model.account.DepositEntity;
import com.finki.websavings.persistence.model.account.InvestmentAccountEntity;
import com.finki.websavings.persistence.model.account.RegularAccountEntity;
import com.finki.websavings.persistence.model.account.SecondPillarEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Domain mapper for the account domain model to the dto and vice versa.
 */
@Component
public class AccountDomainMapper {

  /**
   * Maps the dto to the account domain model.
   *
   * @param account the account dto.
   * @return the domain model.
   */
  public SavingAccountDomainModel toDomainModel(Account account) {

    SavingAccountDomainModel savingAccountDomainModel = getInstance(account);

    savingAccountDomainModel.setCurrency(account.getCurrency());
    savingAccountDomainModel.setInitialValue(account.getInitialValue().doubleValue());
    savingAccountDomainModel.setGrowthRate(account.getGrowthRate().doubleValue());
    savingAccountDomainModel.setDescription(account.getDescription());

    SavingInstitutionDomainModel savingInstitutionDomainModel = new SavingInstitutionDomainModel();
    Bank bank = account.getBank();
    savingInstitutionDomainModel.setName(bank.getName());
    savingInstitutionDomainModel.setType(bank.getType());
    savingAccountDomainModel.setSavingInstitutionDomainModel(savingInstitutionDomainModel);

    if(savingAccountDomainModel instanceof InvestmentAccountDomainModel) {
      double riskFactor = account.getRiskFactor().doubleValue();
      ((InvestmentAccountDomainModel) savingAccountDomainModel).setRiskFactor(riskFactor);
    }

    return savingAccountDomainModel;
  }

  private SavingAccountDomainModel getInstance(Account account) {

    if (account.getType() == Account.TypeEnum.SECOND_PILLAR) {
      return new SecondPillarDomainModel();
    } else if (account.getType() == Account.TypeEnum.REGULAR_ACCOUNT) {
      return new RegularAccountDomainModel();
    } else if (account.getType() == Account.TypeEnum.INVESTMENT_ACCOUNT) {
      return new InvestmentAccountDomainModel();
    } else  if (account.getType() == Account.TypeEnum.DEPOSIT) {
      return new DepositDomainModel();
    }

    throw new RuntimeException("unsupported type");
  }

  /**
   * Maps to account dto.
   *
   * @param domainModel the domain model.
   * @return the account.
   */
  public Account toAccountDto(SavingAccountDomainModel domainModel) {

    Account account = new Account();

    account.setCurrency(domainModel.getCurrency());
    account.setInitialValue(BigDecimal.valueOf(domainModel.getInitialValue()));
    account.setGrowthRate(BigDecimal.valueOf(domainModel.getGrowthRate()));
    account.setDescription(domainModel.getDescription());
    account.setId(domainModel.getId());

    Bank bank = new Bank();
    SavingInstitutionDomainModel savingInstitutionDomainModel = domainModel.getSavingInstitutionDomainModel();
    bank.setName(savingInstitutionDomainModel.getName());
    bank.setType(savingInstitutionDomainModel.getType());
    account.setBank(bank);

    if(domainModel instanceof InvestmentAccountDomainModel) {
      double riskFactor = ((InvestmentAccountDomainModel) domainModel).getRiskFactor();
      account.setRiskFactor(BigDecimal.valueOf(riskFactor));
    }

    if (domainModel instanceof DepositDomainModel) {
      account.setType(Account.TypeEnum.DEPOSIT);
    } else if (domainModel instanceof RegularAccountDomainModel){
      account.setType(Account.TypeEnum.DEPOSIT);
    } else if (domainModel instanceof SecondPillarDomainModel) {
      account.setType(Account.TypeEnum.DEPOSIT);
    } else if (domainModel instanceof InvestmentAccountDomainModel) {
      account.setType(Account.TypeEnum.DEPOSIT);
    }

    return account;
  }

}
