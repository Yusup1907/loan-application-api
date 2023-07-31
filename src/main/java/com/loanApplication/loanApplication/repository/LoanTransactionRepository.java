package com.loanApplication.loanApplication.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.loanApplication.loanApplication.model.LoanTransaction;

@Repository
public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, Long> {
    
    @Modifying
    @Query(value = "INSERT INTO trx_loan (customer_id, loan_date, due_date, amount, description, status, created_at, updated_at) " +
                   "VALUES (:customerId, :loanDate, :paymentDate, :dueDate, :amount, :description, :status, :payment, :repayment, :createdAt, :updatedAt)", nativeQuery = true)
    void insertLoanTransaction(Long customerId, LocalDateTime loanDate, LocalDateTime dueDate,
                               double amount, String description, boolean status,
                               LocalDateTime createdAt, LocalDateTime updatedAt);
}


