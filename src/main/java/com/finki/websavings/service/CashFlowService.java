package com.finki.websavings.service;

import com.finki.websavings.domain.mapper.CashFlowDomainMapper;
import com.finki.websavings.model.CashFlow;
import com.finki.websavings.domain.model.cashflow.CashFlowDomainModel;
import com.finki.websavings.persistence.mapper.CashFlowPersistenceMapper;
import com.finki.websavings.persistence.model.cashflow.CashFlowEntity;
import com.finki.websavings.persistence.model.cashflow.ExpenseEntity;
import com.finki.websavings.persistence.model.cashflow.IncomeEntity;
import com.finki.websavings.persistence.repository.cashflow.ExpenseCashFlowRepository;
import com.finki.websavings.persistence.repository.cashflow.IncomeCashFlowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for the Income cash flow.
 */
@AllArgsConstructor
@Component
public class CashFlowService {

  private final CashFlowDomainMapper domainMapper;
  private final CashFlowPersistenceMapper persistenceMapper;
  private final IncomeCashFlowRepository incomeCashFlowRepository;
  private final ExpenseCashFlowRepository expenseCashFlowRepository;

  /**
   * Saves or updated the cash flow for the given customer if.
   *
   * @param cashFlow the cash flow.
   * @param customerId the customer id.
   */
  public void saveCashFlow(CashFlow cashFlow, Integer customerId) {

    CashFlowDomainModel cashFlowDomainModel = domainMapper.mapDtoToDomainModel(cashFlow);

    CashFlowEntity cashFlowEntity = persistenceMapper.mapToEntity(cashFlowDomainModel, customerId);

    if (cashFlowEntity instanceof IncomeEntity) {
      incomeCashFlowRepository.save((IncomeEntity) cashFlowEntity);
    } else {
      expenseCashFlowRepository.save((ExpenseEntity) cashFlowEntity);
    }
  }

  /**
   * Returns all the cashflows for the given customer id.
   *
   * @param customerId the customer id.
   * @return the cash flows.
   */
  public List<CashFlow> getCashFlows(Integer customerId) {

    List<CashFlowEntity> cashFlowEntities = new ArrayList<>();
    cashFlowEntities.addAll(incomeCashFlowRepository.findAllByCustomerId(customerId));
    cashFlowEntities.addAll(expenseCashFlowRepository.findAllByCustomerId(customerId));

    List<CashFlowDomainModel> domainModels = persistenceMapper.mapToDomainModels(cashFlowEntities);

    return domainMapper.mapToDtos(domainModels);
  }

  /**
   * Deletes the cash flow for the given id.
   *
   * @param cashFlow the cashflow id.
   */
  public void deleteCashFlow(Integer cashFlow) {
    incomeCashFlowRepository.deleteById(cashFlow);
    expenseCashFlowRepository.deleteById(cashFlow);
  }
}