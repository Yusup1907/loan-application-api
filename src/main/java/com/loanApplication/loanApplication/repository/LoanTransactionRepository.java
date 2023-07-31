package com.loanApplication.loanApplication.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loanApplication.loanApplication.model.LoanTransaction;

@Repository
public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, Long> {

    @Modifying
    @Query(value = "INSERT INTO trx_loan (customer_id, loan_date, due_date, amount, description, status, repayment, created_at, updated_at) "
            +
            "VALUES (:customerId, :loanDate, :paymentDate, :dueDate, :amount, :description, :status, :payment, :repayment, :createdAt, :updatedAt)", nativeQuery = true)
    void insertLoanTransaction(Long customerId, LocalDateTime loanDate, LocalDateTime dueDate,
            double amount, String description, boolean status, String repayment,
            LocalDateTime createdAt, LocalDateTime updatedAt);

    @Query("SELECT lt FROM LoanTransaction lt WHERE lt.repayment = :repayment")
    List<LoanTransaction> findAllByRepaymentStatus(@Param("repayment") String repayment);

    @Modifying
    @Query(value = "UPDATE trx_loan SET customer_id = ?1, repayment_date = ?2, payment = ?3, repayment = ?4, updated_at = ?5 " +
            "WHERE id = ?6", nativeQuery = true)
    void updateLoanTransaction(Long customerId, LocalDateTime paymentDate, double payment,
                               String repayment, LocalDateTime updatedAt, Long Id);    

}