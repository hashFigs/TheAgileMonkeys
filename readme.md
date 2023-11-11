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
### Login
 ```console
     POST http://localhost:8080/auth/login
 ```
### Create Customer
 ```console
     POST http://localhost:8080/customers
 ```
### Update Customer
 ```console
     PUT http://localhost:8080/customers
 ```
### Delete Customer
 ```console
     DELETE http://localhost:8080/customers/{CUSTOMER-ID}
 ```

### Create User
 ```console
     POST http://localhost:8080/user
 ```

### Change User Role
 ```console
     PUT http://localhost:8080/user/{USER-ID}
 ``` 