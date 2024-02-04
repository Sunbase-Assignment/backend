package com.example.SunbaseAssignment.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true,nullable = false)
    private String uuid;
    private String firstName;
    private String lastName;
    private String street;
    private String address;
    private String city;
    private String state;
    @Column(unique = true,nullable = false)
    private String email;
    private String phone;

}
