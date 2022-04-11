package com.customer.service.customer.aggregate.doubles.factories;

import com.customer.service.customer.aggregate.domain.Customer;
import com.customer.service.customer.aggregate.domain.dto.CustomerDto;
import com.customer.service.customer.aggregate.doubles.builders.CustomerBuilder;

public class CustomerStubCreator {
    public static Customer createCustomer() {
        return CustomerBuilder.getInstance()
                .build();
    }

    public static CustomerDto createCustomerDto() {
        return CustomerBuilder.getInstance()
                .buildDto();
    }
}
