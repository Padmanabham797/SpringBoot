package com.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_email")
    private String email;

    // One-to-One mapping
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private PassportEntity passport;

@OneToMany(mappedBy ="customer",cascade = CascadeType.ALL,orphanRemoval = true)
private List<OrderEntity>orders=new ArrayList<>();


  @ManyToMany
  @JoinTable(
          name = "customer_role",
          joinColumns = @JoinColumn(name = "custome_id"),
          inverseJoinColumns =@JoinColumn(name="role_id")
  )
  private Set<RoleEntity> roles=new HashSet<>();

    public CustomerEntity(){
    }

    public CustomerEntity(Long id, String name,String email) {
        this.id = id;
        this.name = name;
        this.email=email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<OrderEntity> getOrders() { return orders; }
    public String getEmail(){return email;}

    public void setEmail(String email) {
        this.email = email;


    }
    public void addOrder(OrderEntity order){
        orders.add(order);
        order.setCustomer(this);
    }
    public void removeOver(OrderEntity order){
        orders.remove(order);
        order.setCustomer(null);
    }

    public Set<RoleEntity> getRoles() { return roles; }
    public void setRoles(Set<RoleEntity> roles) { this.roles = roles; }

    public PassportEntity getPassport() { return passport; }
    public void setPassport(PassportEntity passport) { this.passport = passport; }
}
