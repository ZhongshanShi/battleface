package com.battleface.codeChallengeJay.repository;

import com.battleface.codeChallengeJay.model.QuotationToCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<QuotationToCustomer, String> {
}
