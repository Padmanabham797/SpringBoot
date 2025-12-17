package com.example.controller;

import com.example.Service.CustomerService;
import com.example.entity.CustomerEntity;
import com.example.entity.PassportEntity;
import com.example.model.Customer;
import com.example.model.Order;
import com.example.model.Role;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/h")
public class CustomerController {

    private CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerService.getCustomer(id);
    }
    @GetMapping("/customers")
    public List<Customer>getAllCustomerName(@RequestParam("name")String name){
        return customerService.getAllCustomerName(name);
    }
    @GetMapping("/customer/all")
    public List<Customer> getAllCustomers() {
      return customerService.getAllCustomers();
    }

    @GetMapping("/customer")
    public Customer getCustomers(@RequestParam("name") String name,@RequestParam("id") Long id) {
        return customerService.getCustomers(name,id);

    }
    @PostMapping("/customer/create")
    public String createUser(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }


    @PostMapping("/{userId}/orders")
    public Order createOrder(@PathVariable Long userId, @RequestBody Order order) {
        return customerService.createOrder(userId,order);
    }
    @PutMapping("/customer/update/{id}")
    public String updateCustomer(@RequestBody Customer customer,@PathVariable Long id){
        return customerService.updateCustomer(customer, id);
    }

    @DeleteMapping("/customer/{id}")
    public String deleteUser(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }
    @PostMapping("/customers/roles")
    public Role createRole(@RequestBody Role role) {
        return customerService.createRole(role);
    }

    @PostMapping("/customers/{customerId}/roles/{roleId}")
    public Customer assignRoleToUser(@PathVariable Long customerId, @PathVariable Long roleId) {
        return customerService.assignRoleToUser(customerId, roleId);
    }

    @PostMapping("/passports")
    public PassportEntity createPassport(@RequestBody PassportEntity passport) {
        return customerService.createPassport(passport);
    }
    @PostMapping("/{customerId}/passport/{passportId}")
    public CustomerEntity assignPassportToUser(@PathVariable Long customerId, @PathVariable Long passportId) {
        return customerService.assignPassportToUser(customerId, passportId);
    }
    @GetMapping("/customers/page")
    public List<Customer> getAllCustomersList(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        return customerService.getCustomers(pageNumber, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/customers/email")
    public Customer getAllCustomerEmail(@RequestParam("email") String email) {
        return customerService.getCustomerByEmail(email);

    }

    @DeleteMapping("/customers/email")
    public String deleteMapping(@RequestParam("email") String email) {
        return customerService.deleteEmail(email);

    }

    @PutMapping("/customers/email/{id}")
    public String updateEmailByID(@PathVariable Long id,@RequestParam("email") String email) {
        return customerService.updateCustomerEmailById(id, email);

    }

}
