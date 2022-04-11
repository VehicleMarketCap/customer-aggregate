package com.customer.service.customer.aggregate.doubles.factories;

import com.customer.service.customer.aggregate.domain.Customer;
import com.customer.service.customer.aggregate.domain.dto.CustomerDto;
import com.customer.service.customer.aggregate.doubles.builders.CustomerBuilder;

import java.util.ArrayList;
import java.util.List;

public class CustomerStubCreator {
    public static Customer createCustomer() {
        return CustomerBuilder.getInstance()
                .build();
    }

    public static CustomerDto createCustomerDto() {
        return CustomerBuilder.getInstance()
                .buildDto();
    }

    public static CustomerDto createCustomerDtoWithMissingName() {
        return CustomerBuilder.getInstance()
                .withName(null)
                .buildDto();
    }

    public static List<CustomerDto> createCustomerDtos() {
        List<CustomerDto> customers = new ArrayList<>();
        customers.add(createCustomerDto());
        customers.add(createWithNameEmailAndPhone("Perj", "pfranc@yahoo.com", "23445438700"));
        return customers;
    }

    public static CustomerDto createWithNameEmailAndPhone(String name, String email, String phone) {
        return CustomerBuilder.getInstance()
                .withName(name)
                .withEmail(email)
                .withPhoneNumber(phone)
                .buildDto();
    }
}
