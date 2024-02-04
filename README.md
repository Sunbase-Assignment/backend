# Customer CRUD Application

## Overview

This is a CRUD (Create, Read, Update, Delete) application for managing customer data. The application utilizes MySQL as the database, Spring Boot for the backend, and HTML/CSS/JS/React.js for the frontend. JWT authentication is implemented for secure access to the APIs.

## Backend

### Technologies Used
- Backend: JSP Servlet / Spring Boot
- Database: MySQL
- Authentication: JWT

### API Endpoints

#### Sign in
```http
  POST /admin/authenticate

#### Create a Customer
```http
   POST /customer/createCustomer/id={id}

#### Update a customer
```http
   PUT /customer/updateCustomer

#### Get a List of Customers (with Pagination, Sorting, and Searching)
```http
   GET /customer/listOfCustomers/page={page}&size={size}&searchBy={field}&search={searchTerm}

#### Get a Single Customer based on ID
```http
   GET /customer/customerById/id={id}

#### Add List of customer data to data base
```http
   POST /customer/addListOfDataToDb

#### Delete a customer
```http
   DELETE /customer/deleteCustomer
