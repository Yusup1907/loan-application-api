// package com.loanApplication.loanApplication.handler;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.loanApplication.loanApplication.model.LoanTransaction;
// import com.loanApplication.loanApplication.service.LoanTransactionService;
// import com.loanApplication.loanApplication.service.PaymentService;

// @RestController
// @RequestMapping("/api")
// public class PaymentHandler {

//     private final PaymentService paymentService;

//     @Autowired
//     public PaymentHandler(PaymentService paymentService) {
//         this.paymentService = paymentService;
//     }

//     // @PostMapping("/{transactionId}/payment")
//     // public ResponseEntity<?> makePayment(@PathVariable("transactionId") Long transactionId, @RequestBody PaymentRequest paymentRequest) {
//     //     try {
//     //         LoanTransaction loanTransaction = LoanTransactionService.makePayment(transactionId, paymentRequest.getPaymentDate(), paymentRequest.getPayment());

//     //         return new ResponseEntity<>(loanTransaction, HttpStatus.OK);
//     //     } catch (IllegalArgumentException e) {
//     //         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//     //     }
//     // }

//     // Inner class for representing payment reques
// }
