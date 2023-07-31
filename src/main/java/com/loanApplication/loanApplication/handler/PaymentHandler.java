package com.loanApplication.loanApplication.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanApplication.loanApplication.model.LoanTransaction;
import com.loanApplication.loanApplication.service.PaymentService;

@RestController
@RequestMapping("/api")
public class PaymentHandler {

    private final PaymentService paymentService;

    @Autowired
    public PaymentHandler(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/{Id}/payment")
    public ResponseEntity<LoanTransaction> makePayment(@PathVariable Long Id, @RequestBody double payment) {
        try {
            LoanTransaction updatedLoanTransaction = paymentService.makePayment(Id, payment);
            return new ResponseEntity<>(updatedLoanTransaction, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
