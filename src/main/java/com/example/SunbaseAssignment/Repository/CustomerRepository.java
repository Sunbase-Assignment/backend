package com.example.SunbaseAssignment.Repository;

import com.example.SunbaseAssignment.Models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Page<Customer> findByFirstNameContaining(String searchTerm, Pageable pageable);
    Page<Customer> findByCityContaining(String searchTerm, Pageable pageable);
    Page<Customer> findByEmailContaining(String searchTerm, Pageable pageable);
    Page<Customer> findByPhoneContaining(String searchTerm, Pageable pageable);
    Optional<Customer> findByUuid(String s);
    void deleteByUuid(String id);
}
