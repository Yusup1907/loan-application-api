package com.loanApplication.loanApplication.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "trx_loan")
public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "loan_date")
    private LocalDateTime loanDate;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "amount")
    private double amount;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private boolean status;

    @Column(name = "payment")
    private double payment;

    @Column(name = "repayment")
    private String repayment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public LoanTransaction() {
        // Default constructor
    }

    public LoanTransaction(Customer customer, LocalDateTime loanDate, LocalDateTime dueDate, double amount,
                           String description, boolean status, double payment, String repayment, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.customer = customer;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.payment = payment;
        this.repayment = repayment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Constructor for denied loan without payment and repayment
    public LoanTransaction(Customer customer, LocalDateTime loanDate, LocalDateTime dueDate, double amount,
                           String description, boolean status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.customer = customer;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
