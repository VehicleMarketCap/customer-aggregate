package com.customer.service.customer.aggregate.controller;

import com.customer.service.customer.aggregate.domain.Customer;
import com.customer.service.customer.aggregate.domain.dto.CustomerDto;
import com.customer.service.customer.aggregate.mapper.CustomerMapper;
import com.customer.service.customer.aggregate.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Create new customer")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto response = customerService.create(customerDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all existing customer")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        List<CustomerDto> customers = customerService.getAll();
        return new ResponseEntity<>(customers, customers.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

}
