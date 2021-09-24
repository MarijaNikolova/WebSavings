package com.finki.websavings.service;

import com.finki.websavings.domain.mapper.AccountDomainMapper;
import com.finki.websavings.domain.model.account.SavingAccountDomainModel;
import com.finki.websavings.domain.model.account.SavingInstitutionDomainModel;
import com.finki.websavings.model.Account;
import com.finki.websavings.persistence.mapper.AccountPersistenceMapper;
import com.finki.websavings.persistence.mapper.SavingInstitutionPersistenceMapper;
import com.finki.websavings.persistence.model.account.SavingAccountEntity;
import com.finki.websavings.persistence.model.account.SavingInstitutionEntity;
import com.finki.websavings.persistence.repository.account.SavingAccountRepository;
import com.finki.websavings.persistence.repository.account.SavingInstitutionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Account service.
 */
@AllArgsConstructor
@Component
public class AccountService {

  private final AccountDomainMapper accountDomainMapper;
  private final AccountPersistenceMapper accountPersistenceMapper;
  private final SavingAccountRepository repository;
  private final SavingInstitutionPersistenceMapper savingInstitutionPersistenceMapper;
  private final SavingInstitutionRepository savingInstitutionRepository;

  /**
   * Saves the account for the given customer.
   *
   * @param account the account.
   * @param customerId the customer id.
   */
  public void saveAccount(Account account, Integer customerId) {

    SavingAccountDomainModel savingAccountDomainModel = accountDomainMapper.toDomainModel(account);

    SavingInstitutionEntity savingInstitutionDomainModel =
      savingInstitutionPersistenceMapper.toEntity(savingAccountDomainModel.getSavingInstitutionDomainModel());
    SavingInstitutionEntity savingInstitutionEntity = savingInstitutionRepository.save(savingInstitutionDomainModel);

    SavingAccountEntity savingAccountEntity =
      accountPersistenceMapper.toEntity(savingAccountDomainModel, customerId, savingInstitutionEntity);

    repository.save(savingAccountEntity);
  }

  /**
   * Retrieves all accounts for the given customer id.
   *
   * @param customerId the customer id.
   * @return the accounts list.
   */
  public List<Account> getAllAccounts(Integer customerId) {

    List<SavingAccountEntity> allByCustomerId = repository.getAllByCustomerId(customerId);

    List<SavingAccountDomainModel> collect =
      allByCustomerId.stream().map(accountPersistenceMapper::toDomainModel).collect(Collectors.toList());

    return collect.stream().map(accountDomainMapper::toAccountDto).collect(Collectors.toList());
  }
}
