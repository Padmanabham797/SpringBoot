package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "passports")
public class PassportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNumber;

    @OneToOne(mappedBy = "passport")
    private CustomerEntity customerEntity;

    public PassportEntity() {}

    public PassportEntity(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }

    public CustomerEntity getUser() { return customerEntity; }
    public void setUser(CustomerEntity user) { this.customerEntity= user; }
}
