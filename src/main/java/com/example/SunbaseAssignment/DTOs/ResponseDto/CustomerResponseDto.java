package com.example.SunbaseAssignment.DTOs.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {
    private String uuid;
    private String firstname;
    private String lastname;
    private String street;
    private String address;
    private String city;
    private String state;
    private String email;
    private String phone;
}
