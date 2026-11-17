package com.example.accessingdatajpa.service;

import com.example.accessingdatajpa.dto.CustomerDto;
import com.example.accessingdatajpa.model.Customer;
import com.example.accessingdatajpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers() {
        List<Customer> lc = (List<Customer>) customerRepository.findAll();
        List<CustomerDto> lcdto = lc.stream()
                .map(c -> new CustomerDto(c.getFirstName(), c.getLastName()))
                .toList();
        return lcdto;
    }

    public CustomerDto getCustomerById(long id) {
        Customer c = customerRepository.findById(id);
        CustomerDto cdto = new CustomerDto(c.getFirstName(), c.getLastName());
        return cdto;
    }

    public List<CustomerDto> getCustomersByLastName(String name) {
        List<Customer> lc = (List<Customer>) customerRepository.findByLastName(name);
        List<CustomerDto> lcdto = lc.stream()
                .map(c -> new CustomerDto(c.getFirstName(), c.getLastName()))
                .toList();
        return lcdto;
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer c = new Customer(customerDto.first_name(), customerDto.last_name());
        Customer customer = customerRepository.save(c);
        return new CustomerDto(customer.getFirstName(), customer.getLastName());
    }

}