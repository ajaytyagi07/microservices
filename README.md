# Spring Boot Microservices Project

## 📌 Overview

This project is a Microservices-based application built using:

- Spring Boot
- Spring Security
- JWT Authentication
- Spring Cloud Gateway
- MySQL
- Maven

The project demonstrates authentication, authorization, API Gateway routing, and inter-service communication.

---

## 🏗 Architecture

The system consists of three main services:

1️⃣ Auth Service  
2️⃣ Product Service  
3️⃣ API Gateway  

Client → Gateway → Auth/Product Services

---

## 🔐 Auth Service (Port: 5001)

Handles:
- User Registration
- User Login
- JWT Token Generation
- Token Validation

### Endpoints:

POST `/auth/register`  
POST `/auth/login`  
GET `/auth/validate-token`

---

## 📦 Product Service (Port: 5002)

Handles:
- Create Product
- Get Products
- Update Product
- Delete Product

Product APIs are secured and require JWT token.

---

## 🚪 API Gateway (Port: 5000)

Handles:
- Routing to services
- JWT validation before forwarding requests
- Central entry point for all APIs

### Routes:

- `/auth/**` → Auth Service
- `/product/**` → Product Service

---

## 📚 API Documentation (Swagger)

Swagger UI is integrated for API testing and documentation.

Once the services are running, you can access:

Auth Service Swagger:
http://localhost:5001/swagger-ui.html

Product Service Swagger:
http://localhost:5002/swagger-ui.html

You can:
- View all available endpoints
- Test APIs directly from the browser
- Provide JWT token in Authorization header

---

## 🛠 Technologies Used

- Java 21
- Spring Boot
- Spring Security
- MySQL
- Spring Cloud Gateway
- JWT (JSON Web Token)
- Maven


