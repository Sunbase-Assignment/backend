package com.example.SunbaseAssignment.Repository;

import com.example.SunbaseAssignment.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);
}
