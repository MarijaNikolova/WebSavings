package com.finki.websavings.persistence.mapper;

import com.finki.websavings.model.cashflow.CashFlowDomainModel;
import com.finki.websavings.model.cashflow.ExpenseCashFlowDomainModel;
import com.finki.websavings.model.cashflow.IncomeCashFlowDomainModel;
import com.finki.websavings.persistence.model.cashflow.CashFlowEntity;
import com.finki.websavings.persistence.model.cashflow.ExpenseEntity;
import com.finki.websavings.persistence.model.cashflow.IncomeEntity;
import com.finki.websavings.persistence.model.customer.CustomerEntity;
import com.finki.websavings.util.DateUtility;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Maps from entities to domain models and vice versa.
 */
@Component
public class CashFlowPersistenceMapper {

  /**
   * Maps the given entities into domain models.
   *
   * @param cashFlowEntities the entities.
   * @return the domain models.
   */
  public List<CashFlowDomainModel> mapToDomainModels(List<CashFlowEntity> cashFlowEntities) {

    return cashFlowEntities
      .stream()
      .map(this::mapToCashFlowDomainModel)
      .collect(Collectors.toList());
  }

  private CashFlowDomainModel mapToCashFlowDomainModel(CashFlowEntity cashFlowEntity) {

    CashFlowDomainModel cashFlowDomainModel =
      cashFlowEntity instanceof ExpenseEntity ? new ExpenseCashFlowDomainModel() : new IncomeCashFlowDomainModel();

    cashFlowDomainModel.setCategory(cashFlowEntity.getCategory());
    cashFlowDomainModel.setOccurrenceType(cashFlowEntity.getOccurrenceType());
    cashFlowDomainModel.setDescription(cashFlowEntity.getDescription());
    cashFlowDomainModel.setValue(cashFlowEntity.getValue());
    cashFlowDomainModel.setId(cashFlowEntity.getId());
    cashFlowDomainModel.setCurrency(cashFlowEntity.getCurrency());
    cashFlowDomainModel.setDateFrom(DateUtility.fromIsoDateString(cashFlowEntity.getDateFrom()));
    cashFlowDomainModel.setDateTo(DateUtility.fromIsoDateString(cashFlowEntity.getDateTo()));

    return cashFlowDomainModel;
  }

  /**
   * Maps the given cash flow domain model to entity.
   *
   * @param cashFlowDomainModel the cash flow domain model.
   * @return the entity.
   */
  public CashFlowEntity mapToEntity(CashFlowDomainModel cashFlowDomainModel, Integer customerId) {

    CashFlowEntity entity =
      cashFlowDomainModel instanceof IncomeCashFlowDomainModel ? new IncomeEntity() : new ExpenseEntity();

    entity.setCategory(cashFlowDomainModel.getCategory());
    entity.setOccurrenceType(cashFlowDomainModel.getOccurrenceType());
    entity.setDescription(cashFlowDomainModel.getDescription());
    entity.setValue(cashFlowDomainModel.getValue());
    entity.setCurrency(cashFlowDomainModel.getCurrency());
    entity.setId(cashFlowDomainModel.getId());
    entity.setDateFrom(DateUtility.convertDateToIsoStringFormat(cashFlowDomainModel.getDateFrom()));
    entity.setDateTo(DateUtility.convertDateToIsoStringFormat(cashFlowDomainModel.getDateTo()));

    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(customerId);
    entity.setCustomer(customerEntity);

    return entity;
  }
}