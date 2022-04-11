package com.customer.service.customer.aggregate.service;

import com.customer.service.customer.aggregate.domain.Customer;
import com.customer.service.customer.aggregate.domain.dto.CustomerDto;
import com.customer.service.customer.aggregate.doubles.factories.CustomerStubCreator;
import com.customer.service.customer.aggregate.mapper.CustomerMapper;
import com.customer.service.customer.aggregate.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void create_returnsCreatedCustomer() {
        Customer expected = CustomerStubCreator.createCustomer();
        CustomerDto receivedDto = CustomerStubCreator.createCustomerDto();

        given(customerMapper.fromDtoToEntity(receivedDto)).willReturn(expected);
        given(customerRepository.save(expected)).willReturn(expected);

        CustomerDto actual = customerService.create(receivedDto);

        assertThat(actual)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);
    }

    @Test
    public void getAll_whenCustomersExist_returnsAll() {
        List<CustomerDto> expected = Collections.singletonList(CustomerStubCreator.createCustomerDto());

        given(customerRepository.findAll()).willReturn(Collections.singletonList(CustomerStubCreator.createCustomer()));

        List<CustomerDto> actual = customerService.getAll();

        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    public void getAll_whenCustomersDoesntExist_returnsEmptyList() {
        given(customerRepository.findAll()).willReturn(Collections.emptyList());

        List<CustomerDto> actual = customerService.getAll();

        assertThat(actual).isEmpty();
    }
}
