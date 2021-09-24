package com.finki.websavings.controller;

import com.finki.websavings.model.CashFlow;
import com.finki.websavings.service.CashFlowService;
import com.finki.websavings.service.CashFlowsApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Rest controller implementation for the Cash flows api.
 */
@AllArgsConstructor
@RestController
public class CashFlowController implements CashFlowsApi {

  private final CashFlowService cashFlowService;

  @Override
  public ResponseEntity<List<CashFlow>> getCashFlows(Integer customerId) {

    List<CashFlow> cashFlows = cashFlowService.getCashFlows(customerId);

    return ResponseEntity.of(Optional.of(cashFlows));
  }

  @Override
  public ResponseEntity<Void> saveCashFlow(Integer customerId, CashFlow body) {

    cashFlowService.saveCashFlow(body, customerId);
    return null;
  }
}