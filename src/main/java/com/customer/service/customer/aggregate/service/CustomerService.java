package com.customer.service.customer.aggregate.service;

import com.customer.service.customer.aggregate.domain.Customer;
import com.customer.service.customer.aggregate.domain.dto.CustomerDto;
import com.customer.service.customer.aggregate.mapper.CustomerMapper;
import com.customer.service.customer.aggregate.repository.CustomerRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = Mappers.getMapper(CustomerMapper.class);
    }

    @Transactional
    public CustomerDto create(CustomerDto customerDto) {
        Customer customer = customerMapper.fromDtoToEntity(customerDto);
        customerRepository.save(customer);
        return customerMapper.fromEntityToDto(customer);
    }

    public List<CustomerDto> getAll() {
        List<Customer> customers = customerRepository.findAll();

        return customerMapper.fromEntitiesToDtos(customers);
    }
}
