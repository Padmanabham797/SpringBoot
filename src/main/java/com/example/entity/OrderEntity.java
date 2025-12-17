package com.example.entity;


import jakarta.persistence.*;

@Entity
@Table(name="orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private CustomerEntity customer;

    public OrderEntity(){}

    public OrderEntity(String productName, Double price) {
        this.productName = productName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }



}
