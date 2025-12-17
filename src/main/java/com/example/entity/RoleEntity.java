package com.example.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;


    @ManyToMany(mappedBy = "roles")
    private Set<CustomerEntity>customers=new HashSet<>();
    public RoleEntity(){}

    public RoleEntity(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<CustomerEntity> getUsers() {
        return customers;
    }

    public void setUsers(Set<CustomerEntity> customers) {
        this.customers = customers;
    }
}
