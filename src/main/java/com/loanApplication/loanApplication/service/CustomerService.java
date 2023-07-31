package com.loanApplication.loanApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanApplication.loanApplication.model.Customer;
import com.loanApplication.loanApplication.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createNewCustomer(Customer customer) {
        if (isValidCustomerData(customer)) {
            return customerRepository.save(customer);
        } else {
            System.out.println("Gagal menyimpan data pelanggan: Data tidak valid.");
            return null;
        }
    }

    private boolean isValidCustomerData(Customer customer) {
        // Perform native validation here
        boolean isValidFullName = customer.getFull_name() != null && customer.getFull_name().length() >= 3;
        boolean isValidPhoneNumber = customer.getPhone_number() != null && customer.getPhone_number().matches("\\d{11,12}");
        boolean isValidNik = customer.getNik() != null && customer.getNik().matches("\\d{16}");
        boolean isValidNoKk = customer.getNo_kk() != null && customer.getNo_kk().matches("\\d{16}");

        return isValidFullName && isValidPhoneNumber && isValidNik && isValidNoKk;
    }

    // tambahkan metode lain untuk operasi lainnya pada Customer jika diperlukan
}
