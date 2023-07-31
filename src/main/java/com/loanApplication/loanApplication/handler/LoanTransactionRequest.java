package com.loanApplication.loanApplication.handler;

public class LoanTransactionRequest {
    private Long customerId;
    private double amount;
    private String description;

    // Constructors, Getters, and Setters

    public LoanTransactionRequest() {
        // Default constructor
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
