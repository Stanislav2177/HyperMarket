# HyperMarket
This repository contains the backend code for managing employees, sales, products, and manufacturers in a hypermarket.

# Project Structure
The project structure is designed to be simple and straightforward, with classes organized into folders based on their purpose. Below, you'll find a brief overview of the main function of each folder:

.config: This folder handles CORS configuration and Swagger UI configuration.

.controller: All controllers can be found in this package, along with their corresponding endpoints.

.dto: This folder contains two custom DTOs (Data Transfer Objects) created for special requirements.

.entity: Here, you'll find all the entities used in the project. These entities are not auto-generated because I'm using an existing prepared database.

.exception: This folder includes custom exceptions that have been added to some of the service classes. Additionally, there is a GlobalExceptionHandling class responsible for responding with a custom ErrorResponseBody that contains necessary information for troubleshooting any problems.

.jwt: This is my initial attempt at implementing JWT (JSON Web Token) security configuration.

.repository: In this folder, you'll find all the interfaces for each table used in the project. These interfaces likely define the database operations.

.service: The service layer is located here and is responsible for managing all processes and logic related to the hypermarket application.

By organizing the code into these folders, the project aims to maintain a clear and structured separation of concerns, making it easier to develop, maintain, and understand the codebase.

# Database scheme

All the information for the database can be found in this link: https://github.com/Stanislav2177/MySQL-Course-Project

In readme file, the last changes are not noted.








          

