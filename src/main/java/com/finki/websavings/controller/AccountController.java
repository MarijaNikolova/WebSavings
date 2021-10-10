package com.finki.websavings.controller;

import com.finki.websavings.model.Account;
import com.finki.websavings.service.AccountService;
import com.finki.websavings.service.AccountsApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Rest Controller - implementation of the Accounts API.
 */
@AllArgsConstructor
@RestController
public class AccountController implements AccountsApi {

  private final AccountService accountService;

  @Override
  public ResponseEntity<Void> saveAccount(Integer customerId, Account body) {

    accountService.saveAccount(body, customerId);

    return null;
  }

  @Override
  public ResponseEntity<List<Account>> getAccounts(Integer customerId) {
    List<Account> accounts = accountService.getAllAccounts(customerId);
    return ResponseEntity.of(Optional.of(accounts));
  }

  @Override
  public ResponseEntity<Void> deleteAccount(Integer id) {
    accountService.deleteAccount(id);
    return null;
  }
}
