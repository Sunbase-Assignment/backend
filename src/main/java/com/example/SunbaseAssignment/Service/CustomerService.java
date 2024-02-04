package com.example.SunbaseAssignment.Service;

import com.example.SunbaseAssignment.DTOs.RequestDTO.CustomerRequestDto;
import com.example.SunbaseAssignment.DTOs.ResponseDto.CustomerResponseDto;
import com.example.SunbaseAssignment.Exception.CustomerNotFountException;
import com.example.SunbaseAssignment.Exception.SearchNotFoundException;
import com.example.SunbaseAssignment.Models.Customer;
import com.example.SunbaseAssignment.Repository.CustomerRepository;
import com.example.SunbaseAssignment.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public String createNewCustomer(CustomerRequestDto customerRequestDto) {

        try{
            Customer customer = CustomerTransformer.convertDtoToEntity(customerRequestDto);
            customer.setUuid(generateRandomUuid());
            customerRepository.save(customer);
            return "Customer created successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CustomerResponseDto getCustomerById(String id) throws CustomerNotFountException {
        Optional<Customer> customerOptional = customerRepository.findByUuid(id);
        if(customerOptional.isEmpty()){
            throw new CustomerNotFountException("Invalid Customer ID");
        }
        Customer customer = customerOptional.get();

        return CustomerTransformer.convertEntityToDto(customer);
    }

    public String deleteCustomer(String id) throws CustomerNotFountException {

        Optional<Customer> customerOptional = customerRepository.findByUuid(id);
        if(customerOptional.isEmpty()) {
            throw new CustomerNotFountException("Invalid Customer ID");
        }

        customerRepository.deleteById(id);

        return "Customer deleted successfully";
    }

    public CustomerResponseDto updateCustomer(String id, CustomerRequestDto customerRequestDto) throws CustomerNotFountException {
        Optional<Customer> customerOptional = customerRepository.findByUuid(id);
        if(customerOptional.isEmpty()) {
            throw new CustomerNotFountException("Invalid Customer ID");
        }

        Customer customer = customerOptional.get();

        CustomerTransformer.updateEntity(customer,customerRequestDto);

        customerRepository.save(customer);

        return CustomerTransformer.convertEntityToDto(customer);
    }

    public Page<CustomerResponseDto> getAllCustomers(int page, int size, String sortField, String sortOrder, String searchTerm, String searchBy) throws SearchNotFoundException {
        Sort.Direction sortDirection = sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));

        if(StringUtils.hasText(searchBy) && StringUtils.hasText(searchTerm)){
            Page<Customer> resultPage = switch (searchBy) {
                case "firstname" -> customerRepository.findByFirstNameContaining(searchTerm,pageable);
                case "city" -> customerRepository.findByCityContaining(searchTerm, pageable);
                case "email" -> customerRepository.findByEmailContaining(searchTerm, pageable);
                case "phone" -> customerRepository.findByPhoneContaining(searchTerm, pageable);
                default -> null;
            };

            if(resultPage == null){
                throw new SearchNotFoundException(searchTerm+" not found");
            }
            return convertToCustomerResponseDtoPage(resultPage);

        } else {
            Page<Customer> resultPage = customerRepository.findAll(pageable);
            return convertToCustomerResponseDtoPage(resultPage);
        }
    }

    private Page<CustomerResponseDto> convertToCustomerResponseDtoPage(Page<Customer> customerPage) {
        List<CustomerResponseDto> customerResponseDtoList = customerPage.getContent()
                .stream()
                .map(CustomerTransformer::convertEntityToDto)
                .collect(Collectors.toList());

        return new org.springframework.data.domain.PageImpl<>(customerResponseDtoList, customerPage.getPageable(), customerPage.getTotalElements());
    }

    public String addDataToDb(CustomerRequestDto[] data) {
        try{
            for(CustomerRequestDto dto : data){
                Customer customer = CustomerTransformer.convertDtoToEntity(dto);
                customer.setUuid(generateRandomUuid());
                customerRepository.save(customer);
            }
            return "Data added to database successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateRandomUuid() {
        StringBuilder uuid = new StringBuilder("xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx");
        Random random = new Random();

        for (int i = 0; i < uuid.length(); i++) {
            char c = uuid.charAt(i);
            if (c == 'x') {
                uuid.setCharAt(i, Character.forDigit(random.nextInt(16), 16));
            } else if (c == 'y') {
                uuid.setCharAt(i, Character.forDigit((random.nextInt(4) | 0x8), 16));
            }
        }

        return uuid.toString();
    }
}
