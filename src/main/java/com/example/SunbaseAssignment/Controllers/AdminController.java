package com.example.SunbaseAssignment.Controllers;

import com.example.SunbaseAssignment.Authenticate.UserInfoDetails;
import com.example.SunbaseAssignment.DTOs.RequestDTO.AdminDto;
import com.example.SunbaseAssignment.DTOs.RequestDTO.AuthRequest;
import com.example.SunbaseAssignment.DTOs.ResponseDto.AuthResponse;
import com.example.SunbaseAssignment.Service.AdminService;
import com.example.SunbaseAssignment.Service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserInfoDetails userInfoDetails;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addAdmin")
    public String createAdmin(@RequestBody AdminDto adminDto){
        return adminService.createAdmin(adminDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        UserDetails userDetails = userInfoDetails.loadUserByUsername(authRequest.getUsername());
        String token = jwtService.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/welcome")
    public String sayHello(){
        return "Welcome to customer I am not authenticated";
    }
}
