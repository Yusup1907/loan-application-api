package com.loanApplication.loanApplication.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerId + " not found."));

        // Validate customer data
        if (!isValidCustomerData(customer)) {
            // If customer data is not valid, set status to "Denied"
            return saveLoanTransaction(customer, LocalDateTime.now(), null, amount, description, false, LocalDateTime.now(), LocalDateTime.now());
        } else {
            // If all customer data is valid, set status to "Approved" and calculate due date
            LocalDateTime loanDate = LocalDateTime.now();
            LocalDateTime dueDate = loanDate.plus(2, ChronoUnit.MONTHS);
            return saveLoanTransaction(customer, loanDate, dueDate, amount, description, true, LocalDateTime.now(), LocalDateTime.now());
        }
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

    private LoanTransaction saveLoanTransaction(Customer customer, LocalDateTime loanDate, LocalDateTime dueDate,
                                                 double amount, String description, boolean status,
                                                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        LoanTransaction loanTransaction = new LoanTransaction(customer, loanDate, dueDate, amount, description, status, createdAt, updatedAt);
        return loanTransactionRepository.save(loanTransaction);
    }
}
