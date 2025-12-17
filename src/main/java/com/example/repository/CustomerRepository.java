package com.example.repository;

import com.example.entity.CustomerEntity;
import com.example.entity.CustomerEntity;
import com.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByNameAndId(String name, Long id);

    List<CustomerEntity> findByName(String name);

    @Query("SELECT u FROM CustomerEntity u WHERE u.email = :email")
    CustomerEntity getCustomerByEmail(@Param("email") String email);


    @Modifying
    @Transactional
    @Query("DELETE FROM CustomerEntity u WHERE u.email = :email")
    int deleteCustomerByEmail(@Param("email") String email);


    @Modifying
    @Transactional
    @Query("UPDATE CustomerEntity u SET u.email = :email WHERE u.id = :id")
    int updateCustomerEmailById(@Param("id") Long id, @Param("email") String email);



}
