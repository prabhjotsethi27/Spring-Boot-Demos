package com.hcl.demo.fundTransferApp.repository;

import com.hcl.demo.fundTransferApp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAll();
    Optional<Account> findByAccountNo(Long accountNo);
    Optional<List<Account>> findByCustomerId(Integer custId);
}
