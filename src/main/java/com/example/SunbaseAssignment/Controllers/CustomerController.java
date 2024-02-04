package com.example.SunbaseAssignment.Controllers;

import com.example.SunbaseAssignment.DTOs.RequestDTO.CustomerRequestDto;
import com.example.SunbaseAssignment.DTOs.ResponseDto.CustomerResponseDto;
import com.example.SunbaseAssignment.Exception.CustomerNotFountException;
import com.example.SunbaseAssignment.Exception.SearchNotFoundException;
import com.example.SunbaseAssignment.Models.Customer;
import com.example.SunbaseAssignment.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/createCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws RuntimeException{
        String response = customerService.createNewCustomer(customerRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestParam("id") String id,@RequestBody CustomerRequestDto customerRequestDto){
        try {
            CustomerResponseDto customerResponseDto = customerService.updateCustomer(id,customerRequestDto);
            return new ResponseEntity<>("Customer with id "+id+" updated Successfully", HttpStatus.OK);
        } catch (CustomerNotFountException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/customerById")
    public CustomerResponseDto getCustomerById(@RequestParam("id") String id) {
        try {
            return customerService.getCustomerById(id);
        } catch (CustomerNotFountException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/listOfCustomers")
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "firstName") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String searchBy){

        Page<CustomerResponseDto> customers = null;
        try {
            customers = customerService.getAllCustomers(page, size, sortField, sortOrder,searchTerm,searchBy);
        } catch (SearchNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<?> deleteCustomer(@RequestParam String id){
        try {
            String response = customerService.deleteCustomer(id);
            return new ResponseEntity<>(response+" with id: "+id, HttpStatus.OK);
        } catch (CustomerNotFountException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/addListOfDataToDb")
    public ResponseEntity<?> addListOfDataToDb(@RequestBody CustomerRequestDto[] data){
        String response = customerService.addDataToDb(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
