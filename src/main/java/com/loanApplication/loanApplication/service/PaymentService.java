package com.loanApplication.loanApplication.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanApplication.loanApplication.model.LoanTransaction;
import com.loanApplication.loanApplication.repository.LoanTransactionRepository;

@Service
public class PaymentService {

    private final LoanTransactionRepository loanTransactionRepository;

    @Autowired
    public PaymentService(LoanTransactionRepository loanTransactionRepository) {
        this.loanTransactionRepository = loanTransactionRepository;
    }

    public LoanTransaction makePayment(Long Id, double payment) {
        Optional<LoanTransaction> optionalLoanTransaction = loanTransactionRepository.findById(Id);
        if (optionalLoanTransaction.isEmpty()) {
            throw new IllegalArgumentException("Loan transaction with Id " + Id + " not found.");
        }

        LoanTransaction loanTransaction = optionalLoanTransaction.get();

        // Validate payment 
        if (payment < 0) {
            throw new IllegalArgumentException("Payment amount cannot be negative.");
        }

        // Validate if payment amount is equal to the remaining debt
        double remainingDebt = loanTransaction.getAmount() - loanTransaction.getPayment();
        if (payment != remainingDebt) {
            throw new IllegalArgumentException("Payment amount should be equal to the remaining debt.");
        }

        // Update payment details
        loanTransaction.setPaymentDate(LocalDateTime.now());
        loanTransaction.setPayment(payment);
        loanTransaction.setRepayment("Lunas");
        loanTransaction.setUpdatedAt(LocalDateTime.now());

        // Save the updated loan transaction
        return loanTransactionRepository.save(loanTransaction);
    }
}
