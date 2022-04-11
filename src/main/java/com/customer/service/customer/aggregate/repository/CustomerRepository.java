package com.customer.service.customer.aggregate.repository;

import com.customer.service.customer.aggregate.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
