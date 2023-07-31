package com.loanApplication.loanApplication.handler;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String paymentDate;
    private double paymentAmount;
}
