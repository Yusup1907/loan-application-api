package com.loanApplication.loanApplication.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanApplication.loanApplication.model.Customer;
import com.loanApplication.loanApplication.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerHandler {
    private final CustomerService customerService;

    @Autowired
    public CustomerHandler(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Handler untuk membuat customer baru
    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createNewCustomer(customer);
        if (createdCustomer != null) {
            String successMessage = "Your data has been successfully added.";
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Handler untuk menampilkan list customer
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
