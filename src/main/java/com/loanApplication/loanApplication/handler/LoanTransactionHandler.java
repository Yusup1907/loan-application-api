package com.loanApplication.loanApplication.handler;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/loan-application")
    public ResponseEntity<List<LoanTransaction>> findAllByRepaymentStatus(
            @RequestParam(name = "repayment", required = false) String repayment) {
        if (repayment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<LoanTransaction> loanTransactions = loanTransactionService.findAllByRepaymentStatus(repayment);

        if (loanTransactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(loanTransactions, HttpStatus.OK);
    }

    
}
