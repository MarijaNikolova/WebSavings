package com.finki.websavings.service;

import com.finki.websavings.domain.model.account.SavingAccountDomainModel;
import com.finki.websavings.domain.model.annualvalue.AnnualValue;
import com.finki.websavings.domain.model.cashflow.CashFlowDomainModel;

import com.finki.websavings.model.AnnualValueDto;
import com.finki.websavings.model.Projection;
import com.finki.websavings.model.ProjectionValues;
import com.finki.websavings.persistence.mapper.AccountPersistenceMapper;
import com.finki.websavings.persistence.mapper.CashFlowPersistenceMapper;
import com.finki.websavings.persistence.model.account.SavingAccountEntity;
import com.finki.websavings.persistence.model.cashflow.CashFlowEntity;
import com.finki.websavings.persistence.repository.account.SavingAccountRepository;
import com.finki.websavings.persistence.repository.cashflow.ExpenseCashFlowRepository;
import com.finki.websavings.persistence.repository.cashflow.IncomeCashFlowRepository;
import com.finki.websavings.persistence.repository.config.ConfigDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Projection service.
 */
@AllArgsConstructor
@Component
public class ProjectionService {

  private final SavingAccountRepository savingAccountRepository;
  private final AccountPersistenceMapper accountPersistenceMapper;
  private final ExpenseCashFlowRepository expenseCashFlowRepository;
  private final IncomeCashFlowRepository incomeCashFlowRepository;
  private final ConfigDataRepository configDataRepository;
  private final CashFlowPersistenceMapper cashFlowPersistenceMapper;

  /**
   * Returns the list of projections.
   *
   * @param customerId the customer id.
   * @return the list of projections.
   */
  public List<Projection> getProjections(Integer customerId) {

    int numberOfYears = configDataRepository.findByCustomerId(customerId).getNumberOfYears();

    List<SavingAccountEntity> savingAccountEntities = savingAccountRepository.getAllByCustomerId(customerId);

    List<SavingAccountDomainModel> savingAccountDomainModels =
      savingAccountEntities.stream().map(accountPersistenceMapper::toDomainModel).collect(Collectors.toList());

    List<Projection> projectionsFromAccounts =
      savingAccountDomainModels
      .stream()
      .map(savingAccountDomainModel -> mapToProjectionModel(savingAccountDomainModel, numberOfYears))
      .collect(Collectors.toList());

    List<CashFlowEntity> cashFlowEntities = new ArrayList<>();
    cashFlowEntities.addAll(incomeCashFlowRepository.findAllByCustomerId(customerId));
    cashFlowEntities.addAll(expenseCashFlowRepository.findAllByCustomerId(customerId));

    List<CashFlowDomainModel> cashFlowDomainModels = cashFlowPersistenceMapper.mapToDomainModels(cashFlowEntities);

    List<Projection> projectionsFromCashFlows =
      cashFlowDomainModels
        .stream()
        .map(cashFlowDomainModel -> mapToProjectionModel(cashFlowDomainModel, numberOfYears))
        .collect(Collectors.toList());

    List<Projection> projections = new ArrayList<>();
    projections.addAll(projectionsFromAccounts);
    projections.addAll(projectionsFromCashFlows);

    return projections;
  }

  private Projection mapToProjectionModel(SavingAccountDomainModel savingAccountDomainModel, int numberOfYears) {

    Projection projection = new Projection();

    projection.setId(savingAccountDomainModel.getId());
    projection.setCategory(Projection.CategoryEnum.ACCOUNT);

    Map<Integer, AnnualValue> annualValueMap = savingAccountDomainModel.getAnnualValues(numberOfYears);

    Map<String, ProjectionValues> annualValueProjection = annualValueMap
      .entrySet()
      .stream()
      .collect(
        Collectors
          .toMap(entry-> entry.getKey().toString(), entry -> mapToAnnualValueDto(entry.getValue(), entry.getKey())));

    projection.setValues(annualValueProjection);

    return projection;
  }

  private ProjectionValues mapToAnnualValueDto(AnnualValue value, Integer year) {

    ProjectionValues projectionValues = new ProjectionValues();

    AnnualValueDto annualValueDto = new AnnualValueDto();
    annualValueDto.setValue(BigDecimal.valueOf(value.getValue()));
    annualValueDto.setCurrency(value.getCurrency());
    annualValueDto.setExpense(BigDecimal.valueOf(value.getExpense()));
    annualValueDto.setIncome(BigDecimal.valueOf(value.getIncome()));

    projectionValues.setYear(year);

    return projectionValues;
  }

  private Projection mapToProjectionModel(CashFlowDomainModel cashFlowDomainModel, int numberOfYears) {

    Projection projection = new Projection();

    projection.setId(cashFlowDomainModel.getId());
    projection.setCategory(Projection.CategoryEnum.CASHFLOW);

    Map<Integer, AnnualValue> annualValueMap = cashFlowDomainModel.getAnnualValues(numberOfYears);

    Map<String, ProjectionValues> annualValueProjection = annualValueMap
      .entrySet()
      .stream()
      .collect(
        Collectors
          .toMap(entry-> entry.getKey().toString(), entry -> mapToAnnualValueDto(entry.getValue(), entry.getKey())));

    projection.setValues(annualValueProjection);

    return projection;
  }
}
