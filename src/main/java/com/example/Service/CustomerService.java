package com.example.Service;

import com.example.entity.CustomerEntity;
import com.example.entity.OrderEntity;
import com.example.entity.PassportEntity;
import com.example.entity.RoleEntity;
import com.example.exception.CustomerNotFoundException;
import com.example.model.Customer;
import com.example.model.Order;
import com.example.model.Role;
import com.example.repository.CustomerRepository;
import com.example.repository.OrderRepository;
import com.example.repository.PassportRepository;
import com.example.repository.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private RoleRepository roleRepository;
    private PassportRepository passportRepository;
    public CustomerService(CustomerRepository customerRepository,OrderRepository orderRepository, RoleRepository roleRepository,PassportRepository passportRepository){
        this.customerRepository=customerRepository;
        this.orderRepository=orderRepository;
        this.roleRepository= roleRepository;
        this.passportRepository= passportRepository;
    }
    public Customer getCustomer(Long id){
        Customer customer=new Customer();
        Optional<CustomerEntity>customerEntity=customerRepository.findById(id);
        if(customerEntity.isEmpty()){
            throw new CustomerNotFoundException("customer not found in system");
        }
        customer.setCustomerId(customerEntity.get().getId().intValue());
        customer.setName(customerEntity.get().getName());
        customer.setEmail(customerEntity.get().getEmail());
        List<OrderEntity>orderEntities=customerEntity.get().getOrders();
        List<Order>orders=new ArrayList<>();
        for(OrderEntity orderEntity:orderEntities){
            Order order=new Order();
            order.setId(orderEntity.getId());
            order.setPrice(orderEntity.getPrice());
            order.setProductName(orderEntity.getProductName());
            orders.add(order);

        }

       customer.setOrders(orders);
        return customer;
    }
    public Customer getCustomers(String name,Long id){
        Customer customer=new Customer();
        CustomerEntity customerEntity=customerRepository.findByNameAndId(name,id);
        customer.setCustomerId(customerEntity.getId().intValue());
        customer.setName(customerEntity.getName());
        customer.setEmail(customerEntity.getEmail());
        return customer;
    }


    public String createCustomer(Customer customer) {
        CustomerEntity customerEntity=new CustomerEntity();
        customerEntity.setName(customer.getName());
        customerEntity.setEmail(customer.getEmail());
        CustomerEntity createdCustomer =customerRepository.save(customerEntity);
        return "customer created: " + createdCustomer.getId();
    }

    public String updateCustomer(Customer customer,Long id){
        CustomerEntity customerEntity=customerRepository.findById(id).get();
        customerEntity.setName(customer.getName());
        customerEntity.setEmail(customer.getEmail());
        CustomerEntity updatedCustomer=customerRepository.save(customerEntity);
        return "customer updated:"+ updatedCustomer.getName();
    }



    public String deleteCustomer(Long id){
        customerRepository.deleteById(id);
        return "customer delete for this "+ id;
    }

    public String deleteEmail(String email) {
        customerRepository.deleteCustomerByEmail(email);
        return "User delete for this " + email;

    }

    public String updateCustomerEmailById(Long id,String email) {
        customerRepository.updateCustomerEmailById(id,email);
        return "User updated email  " + email;

    }


    public List<Customer>getAllCustomerName(String name){
        List <Customer> customerList=new ArrayList<>();
        List <CustomerEntity> customerEntity=customerRepository.findByName(name);
        for(CustomerEntity entity:customerEntity){
            Customer customer=new Customer();
            customer.setCustomerId(entity.getId().intValue());
            customer.setName(entity.getName());
            customer.setEmail(entity.getEmail());
            customerList.add(customer);
        }
        return customerList;
    }
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        List<CustomerEntity> customerEntities = customerRepository.findAll(); // built-in JPA method

        for (CustomerEntity entity : customerEntities) {
            Customer customer = new Customer();
            customer.setCustomerId(entity.getId().intValue());
            customer.setName(entity.getName());
            customer.setEmail(entity.getEmail());
            customerList.add(customer);
        }

        return customerList;
    }
    public Order createOrder(Long customerId, Order order){
        CustomerEntity customerEntity=customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("customer not found"));
        OrderEntity orderEntity=new OrderEntity(order.getProductName(),order.getPrice());
        customerEntity.addOrder(orderEntity);
        OrderEntity createdOrderEntity=orderRepository.save(orderEntity);
        Order order1=new Order(createdOrderEntity.getId(),createdOrderEntity.getProductName(),createdOrderEntity.getPrice());
        return order1;


    }
    public Role createRole(Role role){
        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setRoleName(role.getRoleName());
        RoleEntity createdRoleEntity=roleRepository.save(roleEntity);
        return new Role(createdRoleEntity.getRoleName());
    }
    public Customer assignRoleToUser(Long customerId ,Long roleId){
        Customer customerResponse=new Customer();
        Optional<CustomerEntity> customerOpt=customerRepository.findById(customerId);
        Optional<RoleEntity> roleOpt=roleRepository.findById(roleId);
        if(customerOpt.isPresent() && roleOpt.isPresent()){
            CustomerEntity customer=customerOpt.get();
            RoleEntity role =roleOpt.get();
            customer.getRoles().add(role);
            CustomerEntity customerEntity=customerRepository.save(customer);
            customerResponse.setCustomerId(customerEntity.getId().intValue());
            customerResponse.setName(customerEntity.getName());
            customerResponse.setEmail(customerEntity.getEmail());
            Set<RoleEntity>roles=customerEntity.getRoles();
            Set<Role>roleSet=new HashSet<>();
            for(RoleEntity roleEntity:roles){
                Role role1=new Role();
                role1.setRoleName(roleEntity.getRoleName());
                roleSet.add(role1);

            }
            customerResponse.setRoles(roleSet);

        }else {
            throw new RuntimeException("customer or Role not found!");
        }
        return customerResponse;
    }
    public PassportEntity createPassport(PassportEntity passport) {
        return passportRepository.save(passport);
    }

    public CustomerEntity assignPassportToUser(Long userId, Long passportId) {
        CustomerEntity customer = customerRepository.findById(userId).orElse(null);
        PassportEntity passport = passportRepository.findById(passportId).orElse(null);

        if (customer != null && passport != null) {
            customer.setPassport(passport);
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("User or Passport not found!");
        }
    }
    public List<CustomerEntity> getAllUsers() {
        return customerRepository.findAll();
    }


    public List<Customer>getCustomers(int pageNumber,int pageSize,String sortBy, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<CustomerEntity> customerEntities=customerRepository.findAll(pageable);
        List<Customer> customerList=new ArrayList<>();
        customerEntities.forEach( userEntity -> {
            Customer customer=new Customer();
            customer.setEmail(userEntity.getEmail());
            customer.setName(userEntity.getName());
            customer.setCustomerId(userEntity.getId().intValue());
            customerList.add(customer);

        });
        return customerList;
    }

    public Customer getCustomerByEmail(String email) {
        Customer customer=new Customer();
        CustomerEntity userEntity=customerRepository.getCustomerByEmail(email);
        customer.setCustomerId(userEntity.getId().intValue());
        customer.setName(userEntity.getName());
        customer.setEmail(userEntity.getEmail());
        return customer;

    }



}
