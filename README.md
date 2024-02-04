# Customer CRUD Application

## Overview

This is a CRUD (Create, Read, Update, Delete) application for managing customer data. The application utilizes MySQL as the database, Spring Boot for the backend, and HTML/CSS/JS/React.js for the frontend. JWT authentication is implemented for secure access to the APIs.

## Backend

### Technologies Used
- Backend: Spring Boot
- Database: MySQL
- Authentication: JWT

### API Endpoints

#### Sign in
```http
  POST /admin/authenticate
```
```json
      {
        "username": "your username",
        "password": "your password"
      }
  ```
#### Create a Customer
```http
   POST /customer/createCustomer/id={id}
```
```json
      {
        "first_name": "Jane",
        "last_name": "Doe",
        "street": "Elvnu Street",
        "address": "H no 2",
        "city": "Delhi",
        "state": "Delhi",
        "email": "sam@gmail.com",
        "phone": "12345678"
      }
  ```
#### Update a customer
```http
   PUT /customer/updateCustomer/id={id}
```
```json
      {
        "first_name": "Jane",
        "last_name": "Doe",
        "street": "Elvnu Street",
        "address": "H no 2",
        "city": "Delhi",
        "state": "Delhi",
        "email": "sam@gmail.com",
        "phone": "12345678"
      }
    ```
#### Get a List of Customers (with Pagination, Sorting, and Searching)
```http
   GET /customer/listOfCustomers/page={page}&size={size}&searchBy={field}&search={searchTerm}
```
#### Get a Single Customer based on ID
```http
   GET /customer/customerById/id={id}
```
#### Add List of customer data to data base
```http
   POST /customer/addListOfDataToDb
```
'''json
    [
        {
        "first_name": "Jane",
        "last_name": "Doe",
        "street": "Elvnu Street",
        "address": "H no 2",
        "city": "Delhi",
        "state": "Delhi",
        "email": "sam@gmail.com",
        "phone": "12345678"
      },
      {
        "first_name": "Jane",
        "last_name": "Doe",
        "street": "Elvnu Street",
        "address": "H no 2",
        "city": "Delhi",
        "state": "Delhi",
        "email": "sam@gmail.com",
        "phone": "12345678"
      }
    ]
  ```
#### Delete a customer
```http
   DELETE /customer/deleteCustomer/id={id}
```

#### Add admin
``` http
    POST/admin/addAdmin
## Frontend

### Technologies Used
- Frontend : HTML,CSS,Javascript,ReactJs

### Example of Customer object
```json
{
  "first_name": "Jane",
  "last_name": "Doe",
  "street": "Elvnu Street",
  "address": "H no 2",
  "city": "Delhi",
  "state": "Delhi",
  "email": "sam@gmail.com",
  "phone": "12345678"
}
```

## How To Run

1. Clone the repository to your local system.
2. Add admin credentials to the database using the `POST /admin/addAdmin` API endpoint.
3. Create your front end or use Postman to send requests to the required APIs and utilize the data.

Note: As of now, this application can only run on a local system. Further development is required to deploy the application in the cloud.
