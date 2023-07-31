package com.loanApplication.loanApplication.handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loanApplication.loanApplication.model.LoanTransaction;
import com.loanApplication.loanApplication.service.LoanTransactionService;

import jakarta.persistence.EntityNotFoundException;

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
            LoanTransaction loanTransaction = loanTransactionService.createLoanTransaction(request.getCustomerId(),
                    request.getAmount(), request.getDescription());
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

    @PutMapping("/{customerId}/make-payment")
    public ResponseEntity<String> makePayment(@PathVariable Long customerId,
                                              @RequestBody PaymentRequest paymentRequest) {
        LocalDateTime paymentDate = LocalDateTime.parse(paymentRequest.getPaymentDate());
        double payment = paymentRequest.getPayment();

        try {
            boolean paymentStatus = loanTransactionService.makePayment(customerId, paymentDate, payment);
            if (paymentStatus) {
                return ResponseEntity.ok("Payment was successful.");
            } else {
                return ResponseEntity.badRequest().body("Payment failed.");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/income-report")
    public ResponseEntity<Double> getIncomeReport(
            @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        double totalIncome = loanTransactionService.getIncomeBetweenDates(startDate.toString(), endDate.toString());
        return ResponseEntity.ok(totalIncome);
    }
}
