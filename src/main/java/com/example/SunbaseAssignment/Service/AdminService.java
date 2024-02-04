package com.example.SunbaseAssignment.Service;

import com.example.SunbaseAssignment.DTOs.RequestDTO.AdminDto;
import com.example.SunbaseAssignment.Models.Admin;
import com.example.SunbaseAssignment.Models.Customer;
import com.example.SunbaseAssignment.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String createAdmin(AdminDto adminDto) {

        Admin newAdmin = new Admin();
        newAdmin.setUsername(adminDto.getUsername());
        newAdmin.setPassword(passwordEncoder.encode(adminDto.getPassword()));

        adminRepository.save(newAdmin);

        return "New Admin created Successfully";
    }
}
