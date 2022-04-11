package com.customer.service.customer.aggregate.mapper;

import com.customer.service.customer.aggregate.domain.Customer;
import com.customer.service.customer.aggregate.domain.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @Mapping(target = "id", ignore = true)
    Customer fromDtoToEntity(CustomerDto customerDto);

    CustomerDto fromEntityToDto(Customer customer);

    List<CustomerDto> fromEntitiesToDtos(List<Customer> customers);
}
