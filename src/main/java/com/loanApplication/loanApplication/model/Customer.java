package com.loanApplication.loanApplication.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mst_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 100)
    private String full_name;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "nik", length = 16)
    private String nik;

    @Column(name = "phone_number", length = 13)
    private String phone_number;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "no_kk", length = 16)
    private String no_kk;

    @Column(name = "emergency_name", length = 225)
    private String emergency_name;

    @Column(name = "emergency_contact", length = 255)
    private String emergency_contact;

    @Column(name = "last_salary")
    private Long last_salary;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    // Constructors

    public Customer() {
        // Default constructor
    }

    // Constructor with required fields
    public Customer(String full_name, String address, String nik, String phone_number, Integer user_id, String no_kk, String emergency_name, String emergency_contact, Long last_salary, LocalDateTime created_at, LocalDateTime updated_at) {
        this.full_name = full_name;
        this.address = address;
        this.nik = nik;
        this.phone_number = phone_number;
        this.user_id = user_id;
        this.no_kk = no_kk;
        this.emergency_name = emergency_name;
        this.emergency_contact = emergency_contact;
        this.last_salary = last_salary;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    
}