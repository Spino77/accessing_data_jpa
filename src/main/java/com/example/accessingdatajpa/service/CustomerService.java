package com.example.accessingdatajpa.service;

import com.example.accessingdatajpa.dto.CustomerDto;
import com.example.accessingdatajpa.dto.IncidentDto;
import com.example.accessingdatajpa.model.Customer;
import com.example.accessingdatajpa.model.Incident;
import com.example.accessingdatajpa.repository.CustomerRepository;
import com.example.accessingdatajpa.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers() {
        List<Customer> lc = (List<Customer>) customerRepository.findAll();
        List<CustomerDto> lcdto = lc.stream()
                .map(c -> new CustomerDto(c.getFirstName(), c.getLastName(), c.getEmail(), c.getIncidents()))
                .toList();
        return lcdto;
    }

    public CustomerDto getCustomerById(long id) {
        Customer c = customerRepository.findById(id);
        CustomerDto cdto = new CustomerDto(c.getFirstName(), c.getLastName(), c.getEmail(), c.getIncidents());
        return cdto;
    }

    public List<CustomerDto> getCustomersByLastName(String name) {
        List<Customer> lc = (List<Customer>) customerRepository.findByLastName(name);
        List<CustomerDto> lcdto = lc.stream()
                .map(c -> new CustomerDto(c.getFirstName(), c.getLastName(), c.getEmail(), c.getIncidents()))
                .toList();
        return lcdto;
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer c = new Customer(customerDto.first_name(), customerDto.last_name(), customerDto.email());
        Customer customer = customerRepository.save(c);
        return new CustomerDto(customer.getFirstName(), customer.getLastName(), c.getEmail(), c.getIncidents());
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer c = customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer not found, with ID: " + id)
        );
        c.setFirstName(customerDto.first_name());
        c.setLastName(customerDto.last_name());
        customerRepository.save(c);
        return new CustomerDto(c.getFirstName(), c.getLastName(), c.getEmail(), c.getIncidents());
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer not found, with ID: " + id)
        );
        customerRepository.deleteById(id);
    }
}