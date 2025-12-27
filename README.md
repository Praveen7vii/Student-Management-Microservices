🎓 Student Management System – Microservices Architecture

The Student Management System is a backend application built using Spring Boot Microservices that manages students, courses, and enrollments in a scalable and modular way.

The system follows a distributed architecture where each core domain is implemented as an independent microservice. Eureka Server is used for service discovery, Spring Cloud Config Server provides centralized configuration management, and API Gateway acts as a single entry point for routing all client requests.

This project demonstrates real-world backend development practices including RESTful API design, inter-service communication, JWT-based authentication, pagination, and centralized exception handling.

🧱 Microservices Included
Core Services

Student Service – Manages student information (CRUD operations)

Course Service – Manages course data

Enrollment Service – Handles student-course enrollment logic

Infrastructure Services

Eureka Server – Service Discovery

Config Server – Centralized Configuration

API Gateway – API Routing and Single Entry Point

❗ Admin Server is not included in this project.

🛠 Tech Stack

Java 17

Spring Boot 3.x

Spring Cloud

Spring Data JPA

Spring Security (JWT)

MySQL

Maven

📂 Project Structure
student-management-microservices
├── config-server
├── eureka-server
├── api-gateway
├── student-service
├── course-service
├── enrollment-service
└── README.md

🚀 Architecture Flow

                Client
        (Postman / Frontend)
                       |
                       | HTTP Requests
                       v
                API Gateway
        (Routing, Auth, Filters)
                       |
                       | Service Discovery
                       v
                Eureka Server
                       |
        ---------------------------------
        |               |               |
        v               v               v
  Student Service   Course Service   Enrollment Service
        |               |               |
        v               v               v
   Student DB       Course DB       Enrollment DB
        (MySQL)        (MySQL)           (MySQL)




📦 Dependencies Used
Common Dependencies (Across Services)
<!-- Spring Web -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Spring Data JPA -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- MySQL Driver -->
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
</dependency>

<!-- Eureka Client -->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

<!-- Config Client -->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-config</artifactId>
</dependency>

<!-- Validation -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

▶️ How to Run the Project

Start the services in the following order:

Config Server

Eureka Server

API Gateway

Student Service

Course Service

Enrollment Service

Access APIs through API Gateway:

http://localhost:8080

🧪 API Testing

APIs are tested using Postman

All requests are routed via API Gateway

Each service has a dedicated Postman collection

📌 Key Features

Microservices-based architecture

Centralized configuration management

Service discovery using Eureka

API Gateway routing

Pagination and validation

Global exception handling

Independent databases per service

📝 Notes

Each service is independently deployable

Communication between services is REST-based

Configuration changes can be managed centrally

Designed to demonstrate real-world backend architecture

📬 Future Enhancements

Role-based access control (ADMIN, STUDENT)

Centralized authentication via API Gateway

JWT token validation and refresh mechanism

Fine-grained API authorization

Rate limiting and request filtering

Circuit breaker and retry mechanisms

👨‍💻 Author

Praveen Malge
Full-Stack Developer
