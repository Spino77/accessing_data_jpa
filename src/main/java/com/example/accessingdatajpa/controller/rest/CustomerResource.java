package com.example.accessingdatajpa.controller.rest;

import com.example.accessingdatajpa.dto.CustomerDto;
import com.example.accessingdatajpa.model.Customer;
import com.example.accessingdatajpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CustomerResource.CUSTOMERS)
public class CustomerResource {
    public static final String CUSTOMERS = "/customers";

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers(@RequestParam(name = "lastName", required = false) String lastName) {
        if (lastName != null) {
            return ResponseEntity.ok(customerService.getCustomersByLastName(lastName));
        } else {
            return ResponseEntity.ok(customerService.getAllCustomers());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerbyId(@PathVariable("id") Long id) {
        CustomerDto dto = customerService.getCustomerById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto cdto = customerService.addCustomer(customerDto);
        return ResponseEntity.ok(cdto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
        CustomerDto cdto = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(cdto);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }

}