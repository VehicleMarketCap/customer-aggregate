package com.customer.service.customer.aggregate.doubles.builders;

import com.customer.service.customer.aggregate.domain.Customer;
import com.customer.service.customer.aggregate.domain.dto.CustomerDto;

import java.time.LocalDate;

public class CustomerBuilder {
    private String name = "Franc";
    private String surname = "Fjord";
    private LocalDate birthDate = LocalDate.of(1981, 5, 17);
    private String phoneNumber = "440993827790";
    private String email = "ffranc@yahoo.com";

    private CustomerBuilder customerBuilder() {
        return new CustomerBuilder();
    }

    public static CustomerBuilder getInstance() {
        return new CustomerBuilder();
    }

    public String getName() {
        return name;
    }

    public CustomerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public CustomerBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public CustomerBuilder setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setBirthdate(birthDate);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);

        return customer;
    }

    public CustomerDto buildDto() {
        CustomerDto dto = new CustomerDto();
        dto.setName(name);
        dto.setSurname(surname);
        dto.setBirthdate(birthDate);
        dto.setPhoneNumber(phoneNumber);
        dto.setEmail(email);
        return dto;
    }
}
