package com.finki.websavings.persistence.repository.account;

import com.finki.websavings.persistence.model.account.SavingAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repository for the accounts.
 */
@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccountEntity, Integer> {

  List<SavingAccountEntity> getAllByCustomerId(Integer customerId);
}
