package com.loanApplication.loanApplication.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanApplication.loanApplication.model.LoanTransaction;
import com.loanApplication.loanApplication.service.LoanTransactionService;

@RestController
@RequestMapping("/api")
public class LoanTransactionHandler {

    private final LoanTransactionService loanTransactionService;

    @Autowired
    public LoanTransactionHandler(LoanTransactionService loanTransactionService) {
        this.loanTransactionService = loanTransactionService;
    }

    @PostMapping("/loan-application")
    public ResponseEntity<LoanTransaction> createLoanTransaction(@RequestBody LoanTransactionRequest request) {
        try {
            LoanTransaction loanTransaction = loanTransactionService.createLoanTransaction(request.getCustomerId(), request.getAmount(), request.getDescription());
            return new ResponseEntity<>(loanTransaction, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
