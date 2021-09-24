package com.finki.websavings.persistence.repository.account;

import com.finki.websavings.persistence.model.account.SavingInstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Saving institution repository.
 */
@Repository
public interface SavingInstitutionRepository extends JpaRepository<SavingInstitutionEntity, Integer> {
}
