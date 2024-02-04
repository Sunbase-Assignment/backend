package com.example.SunbaseAssignment.Authenticate;

import com.example.SunbaseAssignment.Models.Admin;
import com.example.SunbaseAssignment.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDetails implements UserDetailsService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);

        if(admin == null) {
            throw new UsernameNotFoundException("user not found with the name" + username);
        }

        return admin;
    }
}
