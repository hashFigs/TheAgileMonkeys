# The Agile Monkeys

## Overview

Back end app with users, and customers. Users can create, manage customers. Users that are admin can manage other users
## Table of Contents

- [Getting Started](#getting-started)
  - [Clone the Repository](#clone-the-repository)
  - [Setup](#setup)
- [Usage](#usage)
  - [How to register](#register)
  - [How to login](#login)
  - [How to Create Customer](#create-customer)
  - [How to list Customers](#update-customer)
  - [How to Update Customer](#update-customer)
  - [How to Delete Customer](#delete-customer)
  - [How to Create User](#create-user)
  - [How to Chnage user role User](#change-user-role)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

List any software, tools, or dependencies that users need to install before using your project.

- [Git](https://git-scm.com/)
- ...

## Getting Started

### Clone the Repository

   Download code from github:
    ```console
      git clone git@github.com:hashFigs/TheAgileMonkeys.git
    ```

### Setup    

    * Create a db into postgres
    * Copy and Rename env_example.properties to env.properties and provide the follow information:

    DB_NAME=
    DB_USERNAME=
    DB_PASSWORD=
    
    AWS_ACCESS_ID=
    AWS_SECRET_KEY=
    AWS_REGION=
    
    Go to AgileApplication class and start the application. 



### Register
 
 ```console
     POST http://localhost:8080/auth/register
 ```

 body Payload:
 ```console
     {
    "username": "jordi99",
    "password": "password99"
     }
 ```

### Login
 ```console
     POST http://localhost:8080/auth/login
 ```
  
  Body Payload:
 ```console
     {
    "username": "jordi99",
    "password": "password99"
     }
 ```

 the reponse of the login call will return a jwt token. You have to include this token as a beared token 
 in your future calls to the api. 

### Create Customer
 ```console
     POST http://localhost:8080/customers
 ```
Body payload (customerpicture file is optional)
from postman select form-data and add the file name for the file type (the customer picture) 
 ```console
     {
    "name": "jordi99",
    "surname": "password99"
    "file": picture 
     }
 ```

### Update Customer
 ```console
     PUT http://localhost:8080/customers
 ```
 same payload than Create user

### Delete Customer
 ```console
     DELETE http://localhost:8080/customers/{CUSTOMER-ID}
 ```

### Create User
 ```console
     POST http://localhost:8080/user/
 ```

  body Payload:
 ```console
     {
    "username": "jordi98",
    "password": "password98"
     }
 ```

### Change User Role
 ```console
     PUT http://localhost:8080/user/change-role
 ``` 
 body Payload:
 ```console
 {
    "username": "jordi",
    "newRole": "USER"

}
 ```