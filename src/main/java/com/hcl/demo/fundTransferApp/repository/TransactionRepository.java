package com.hcl.demo.fundTransferApp.repository;

import com.hcl.demo.fundTransferApp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    List<Transaction> findAll();
    Optional<List<Transaction>> findBySourceAccNoOrDestAccNo(Long sourceAccNo, Long destAccNo);
}
