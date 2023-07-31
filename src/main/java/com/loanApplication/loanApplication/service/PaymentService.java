// package com.loanApplication.loanApplication.service;

// import java.time.LocalDateTime;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.loanApplication.loanApplication.model.LoanTransaction;
// import com.loanApplication.loanApplication.repository.CustomerRepository;
// import com.loanApplication.loanApplication.repository.LoanTransactionRepository;

// import jakarta.persistence.EntityNotFoundException;

// @Service
// public class PaymentService {

//     private final LoanTransactionRepository loanTransactionRepository;
//     private final CustomerRepository customerRepository;

//     @Autowired
//     public PaymentService(LoanTransactionRepository loanTransactionRepository, CustomerRepository customerRepository) {
//         this.loanTransactionRepository = loanTransactionRepository;
//         this.customerRepository = customerRepository;
//     }

//     // public boolean makePayment(Long customerId, LocalDateTime paymentDate, double payment) {
//     //     // Cari data pinjaman berdasarkan customerId
//     //     LoanTransaction loanTransaction = loanTransactionRepository.findByCustomerId(customerId);
//     //     if (loanTransaction == null) {
//     //         throw new EntityNotFoundException("LoanTransaction with customerId " + customerId + " not found.");
//     //     }

//     //     // Validasi apakah sudah melewati masa tenggat
//     //     LocalDateTime dueDate = loanTransaction.getDueDate();
//     //     if (dueDate != null && paymentDate.isAfter(dueDate)) {
//     //         throw new IllegalArgumentException("Payment date cannot exceed the due date.");
//     //     }

//     //     // Validasi apakah pembayaran sesuai dengan amount atau jumlah pinjaman
//     //     double amount = loanTransaction.getAmount();
//     //     if (Math.abs(payment - amount) > 0.001) {
//     //         throw new IllegalArgumentException("Payment amount must be equal to the loan amount.");
//     //     }

//     //     // Jika pembayaran sudah sesuai, update status dan tanggal pembayaran
//     //     loanTransaction.setPayment(payment);
//     //     loanTransaction.setPaymentDate(paymentDate);
//     //     loanTransaction.setRepayment("Lunas");
//     //     loanTransaction.setUpdatedAt(LocalDateTime.now());

//     //     // Simpan perubahan ke database
//     //     loanTransactionRepository.save(loanTransaction);

//     //     return true;
//     }
// // }
