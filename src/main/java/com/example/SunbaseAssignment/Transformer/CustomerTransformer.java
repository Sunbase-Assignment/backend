package com.example.SunbaseAssignment.Transformer;

import com.example.SunbaseAssignment.DTOs.RequestDTO.CustomerRequestDto;
import com.example.SunbaseAssignment.DTOs.ResponseDto.CustomerResponseDto;
import com.example.SunbaseAssignment.Models.Customer;

public class CustomerTransformer {

//    customer.setFirst_name(customerRequestDto.getFirst_name());
//        customer.setLast_name(customerRequestDto.getLast_name());
//        customer.setEmail(customerRequestDto.getEmail());
//        customer.setCity(customerRequestDto.getCity());
//        customer.setAddress(customerRequestDto.getAddress());
//        customer.setPhone(customerRequestDto.getPhone());
//        customer.setState(customerRequestDto.getState());
//        customer.setStreet(customerRequestDto.getStreet());
    public static Customer convertDtoToEntity(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .firstName(customerRequestDto.getFirst_name())
                .lastName(customerRequestDto.getLast_name())
                .email(customerRequestDto.getEmail())
                .city(customerRequestDto.getCity())
                .state(customerRequestDto.getState())
                .phone(customerRequestDto.getPhone())
                .address(customerRequestDto.getAddress())
                .street(customerRequestDto.getStreet()).build();
    }

    public static CustomerResponseDto convertEntityToDto(Customer customer){

        return CustomerResponseDto.builder()
                .uuid(customer.getUuid())
                .firstname(customer.getFirstName())
                .lastname(customer.getLastName())
                .email(customer.getEmail())
                .city(customer.getCity())
                .state(customer.getState())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .street(customer.getStreet()).build();
    }

    public static void updateEntity(Customer customer, CustomerRequestDto customerRequestDto) {

        customer.setFirstName(customerRequestDto.getFirst_name());
        customer.setLastName(customerRequestDto.getLast_name());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setCity(customerRequestDto.getCity());
        customer.setState(customerRequestDto.getState());
        customer.setPhone(customerRequestDto.getPhone());
        customer.setAddress(customerRequestDto.getAddress());
        customer.setStreet(customerRequestDto.getStreet());

    }
}
