package com.customer.service.customeraggregate.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void create_returnsCreatedCustomer() {
        Customer customer = CustomerStubCreator.createCustomer();
        CustomerDto expected = CustomerMapper.fromEntityToDto(customer);

        given(customerRepository.save(customer)).willReturn(customer);

        CustomerDto actual = customerService.create(expected);

        assertThat(actual)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    public void getAll_whenCustomersExist_returnsAll() {
        List<Customer> customers = CustomerStubCreator.createListOfCustomers();
        List<CustomerDto> expected = CustomerMapper.fromEntitiesToDtos(customers);

        given(customerRepository.findAll()).willReturn(customers);

        List<CustomerDto> actual = customerService.getAll();

        assertThat(actual).hasSameElements(expected);
    }

    @Test
    public void getAll_whenCustomersDoesntExist_returnsEmptyList() {
        given(customerRepository.findAll()).willReturn(Collections.emptyList());

        List<CustomerDto> actual = customerService.getAll();

        assertThat(actual).isEmpty();
    }
}
