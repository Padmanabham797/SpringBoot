package com.example.model;

import com.example.validator.ValidUsername;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;
@JsonPropertyOrder({"customerId", "name", "email", "roles"})
public class Customer {
    private int userId;
   // @NotBlank(message = "message is required")
    @ValidUsername
    private String name;
    @Email
    private String email;
    private List<Order> orders;


    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setOrders(List<Order> orders) {
       this.orders=orders;
    }
    public Customer(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email=email;
    }

    public Customer() {

    }

    public int getCustomerId() {
        return userId;
    }

    public void setCustomerId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail(){return email;}

    public void setEmail(String email) {
        this.email = email;
    }
}
