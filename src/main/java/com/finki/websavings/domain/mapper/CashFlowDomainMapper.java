package com.finki.websavings.domain.mapper;

import com.finki.websavings.domain.model.cashflow.CashFlowDomainModel;
import com.finki.websavings.domain.model.cashflow.ExpenseCashFlowDomainModel;
import com.finki.websavings.domain.model.cashflow.IncomeCashFlowDomainModel;
import com.finki.websavings.model.CashFlow;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Domain model mappers for the Cash flows. Maps to dto and entity and vice versa.
 */
@Component
public class CashFlowDomainMapper {

  /**
   * Maps the given dto to the domain model.
   *
   * @param cashFlow the cash flow.
   * @return the domain model.
   */
  public CashFlowDomainModel mapDtoToDomainModel(CashFlow cashFlow) {

    CashFlowDomainModel cashFlowDomainModel =
      cashFlow.getType() == CashFlow.TypeEnum.EXPENSE ?
        new ExpenseCashFlowDomainModel() : new IncomeCashFlowDomainModel();

    cashFlowDomainModel.setCurrency(cashFlow.getCurrency());
    cashFlowDomainModel.setValue(cashFlow.getValue().doubleValue());
    cashFlowDomainModel.setCategory(cashFlow.getCategory());
    cashFlowDomainModel.setOccurrenceType(cashFlow.getOccurenceType().toString());
    cashFlowDomainModel.setDescription(cashFlow.getDescription());
    cashFlowDomainModel.setId(cashFlow.getId());
    cashFlowDomainModel.setDateTo(cashFlow.getDateTo());
    cashFlowDomainModel.setDateFrom(cashFlow.getDateFrom());

    return cashFlowDomainModel;
  }

  /**
   * Maps the domain models to dtos.
   *
   * @param domainModels the domain models.
   * @return the dtos.
   */
  public List<CashFlow> mapToDtos(List<CashFlowDomainModel> domainModels) {

    return domainModels.stream().map(this::mapToDto).collect(Collectors.toList());
  }

  /**
   * Maps the given domain dto to the cash flow dto.
   *
   * @param domainModel the domain model.
   * @return the cash flow.
   */
  public CashFlow mapToDto(CashFlowDomainModel domainModel) {

    CashFlow cashFlow = new CashFlow();

    cashFlow.setCategory(domainModel.getCategory());
    cashFlow.setCurrency(domainModel.getCurrency());
    cashFlow.setId(domainModel.getId());
    cashFlow.setDescription(domainModel.getDescription());
    cashFlow.setValue(BigDecimal.valueOf(domainModel.getValue()));
    cashFlow.setDateFrom(domainModel.getDateFrom());
    cashFlow.setDateTo(domainModel.getDateTo());

    CashFlow.OccurenceTypeEnum occurrenceTypeEnum =
      domainModel
        .getOccurrenceType().equals("RECURRING")
          ? CashFlow.OccurenceTypeEnum.RECURRING : CashFlow.OccurenceTypeEnum.ONE_TIME;
    cashFlow.setOccurenceType(occurrenceTypeEnum);

    CashFlow.TypeEnum typeEnum =
      domainModel instanceof IncomeCashFlowDomainModel ? CashFlow.TypeEnum.INCOME : CashFlow.TypeEnum.EXPENSE;
    cashFlow.setType(typeEnum);

    return cashFlow;
  }
}