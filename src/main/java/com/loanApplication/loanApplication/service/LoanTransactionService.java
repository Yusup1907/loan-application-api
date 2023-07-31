package com.loanApplication.loanApplication.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanApplication.loanApplication.model.Customer;
import com.loanApplication.loanApplication.model.LoanTransaction;
import com.loanApplication.loanApplication.repository.CustomerRepository;
import com.loanApplication.loanApplication.repository.LoanTransactionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LoanTransactionService {

    private final LoanTransactionRepository loanTransactionRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public LoanTransactionService(LoanTransactionRepository loanTransactionRepository, CustomerRepository customerRepository) {
        this.loanTransactionRepository = loanTransactionRepository;
        this.customerRepository = customerRepository;
    }

    public LoanTransaction createLoanTransaction(Long customerId, double amount, String description) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerId + " not found."));

        // Validate customer data
        boolean isValidCustomerData = isValidCustomerData(customer);

        // Set status and dueDate based on validation result
        boolean status = isValidCustomerData;
        String repayment = isValidCustomerData ? "Belum Lunas" : ""; // Set repayment status

        LocalDateTime loanDate = LocalDateTime.now();
        LocalDateTime dueDate = isValidCustomerData ? loanDate.plus(2, ChronoUnit.MONTHS) : null;

        // Save LoanTransaction entity
        LoanTransaction loanTransaction = new LoanTransaction(customer, loanDate, dueDate, amount, description, status, 0.0, repayment, LocalDateTime.now(), LocalDateTime.now());
        return loanTransactionRepository.save(loanTransaction);
    }

    private boolean isValidCustomerData(Customer customer) {
        // Perform validation on customer data
        boolean isValidNik = customer.getNik() != null && customer.getNik().matches("\\d{16}");
        boolean isValidNoKk = customer.getNo_kk() != null && customer.getNo_kk().matches("\\d{16}");
        boolean isValidEmergencyContact = customer.getEmergency_contact() != null && customer.getEmergency_contact().matches("\\d{11,12}");
        boolean isValidEmergencyName = customer.getEmergency_name() != null && !customer.getEmergency_name().isEmpty();
        boolean isValidLastSalary = customer.getLast_salary() != null && customer.getLast_salary() > 0;

        return isValidNik && isValidNoKk && isValidEmergencyContact && isValidEmergencyName && isValidLastSalary;
    }

    public List<LoanTransaction> findAllByRepaymentStatus(String repayment) {
        return loanTransactionRepository.findAllByRepaymentStatus(repayment);
    }

    public boolean makePayment(Long customerId, LocalDateTime paymentDate, double payment) {
        // Cari data pinjaman berdasarkan customerId
        LoanTransaction loanTransaction = loanTransactionRepository.findCustomerById(customerId);
        if (loanTransaction == null) {
            throw new EntityNotFoundException("LoanTransaction dengan customerId " + customerId + " tidak ditemukan.");
        }

        // Validasi apakah sudah melewati masa tenggat
        LocalDateTime dueDate = loanTransaction.getDueDate();
        if (dueDate != null && paymentDate.isAfter(dueDate)) {
            throw new IllegalArgumentException("Tanggal pembayaran tidak boleh melebihi tanggal jatuh tempo.");
        }

        // Validasi apakah pembayaran sesuai dengan amount atau jumlah pinjaman
        double amount = loanTransaction.getAmount();
        if (Math.abs(payment - amount) > 0.001) {
            throw new IllegalArgumentException("Jumlah pembayaran harus sama dengan jumlah pinjaman.");
        }

        // Jika pembayaran sudah sesuai, update status dan tanggal pembayaran
        loanTransaction.setPayment(payment);
        loanTransaction.setPaymentDate(paymentDate);
        loanTransaction.setRepayment("Lunas");
        loanTransaction.setUpdatedAt(LocalDateTime.now());

        // Simpan perubahan ke database
        loanTransactionRepository.save(loanTransaction);

        return true; // Mengembalikan true menunjukkan bahwa pembayaran berhasil
    }
}