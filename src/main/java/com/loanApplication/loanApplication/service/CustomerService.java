package com.loanApplication.loanApplication.service;

import java.util.List;

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
            System.out.println("Gagal menyimpan data customer: Data tidak valid.");
            return null;
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public int updateCustomer(Customer customer) {
        if (isValidCustomerData(customer)) {
            return customerRepository.updateCustomer(
                    customer.getId(),
                    customer.getFull_name(),
                    customer.getAddress(),
                    customer.getNik(),
                    customer.getPhone_number(),
                    customer.getUser_id(),
                    customer.getNo_kk(),
                    customer.getEmergency_name(),
                    customer.getEmergency_contact(),
                    customer.getLast_salary(),
                    customer.getUpdated_at()
            );
        } else {
            System.out.println("Gagal mengupdate data customer: Data tidak valid.");
            return 0;
        }
    }

    private boolean isValidCustomerData(Customer customer) {
        // Perform native validation here
        boolean isValidFullName = customer.getFull_name() != null && customer.getFull_name().length() >= 3;
        boolean isValidPhoneNumber = customer.getPhone_number() != null
                && customer.getPhone_number().matches("\\d{11,12}");
        boolean isValidEmergencyContact = customer.getEmergency_contact() != null
                && customer.getEmergency_contact().matches("\\d{11,12}");
        boolean isValidNik = customer.getNik() != null && customer.getNik().matches("\\d{16}");
        boolean isValidNoKk = customer.getNo_kk() != null && customer.getNo_kk().matches("\\d{16}");

        return isValidFullName && isValidPhoneNumber && isValidEmergencyContact && isValidNik && isValidNoKk;
    }

    // tambahkan metode lain untuk operasi lainnya pada Customer jika diperlukan
}
