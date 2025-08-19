# Forum API

A secure forum application built with Spring Boot 3 featuring authentication, authorization, and RESTful APIs.

## Features

- ✅ JWT-based authentication
- ✅ Role-based authorization (USER, MODERATOR, ADMIN)
- ✅ CRUD operations for topics and comments
- ✅ Search functionality
- ✅ OpenAPI documentation
- ✅ Docker support
- ✅ PostgreSQL database
- ✅ Input validation
- ✅ Pagination

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **JWT**
- **OpenAPI 3**
- **Docker**

## Prerequisites

- Java 17+
- Maven 3.6+
- Docker (optional)
- PostgreSQL (optional)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/forum-app.git
cd forum-app


Configure database in application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/forum_db
spring.datasource.username=your_username
spring.datasource.password=your_password
Build the application:

bash
mvn clean package
Run the application:

bash
java -jar target/forum-app-1.0.0.jar
Docker Deployment
Build and run with Docker Compose:

bash
docker-compose up --build
Access the application at: http://localhost:8080

API Documentation
Once running, access:

Swagger UI: http://localhost:8080/swagger-ui.html

OpenAPI docs: http://localhost:8080/api-docs

Authentication
Register
bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"user1","password":"password123"}'
Login
bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user1","password":"password123"}'
Protected Endpoints
Include the JWT token in the Authorization header:

bash
curl -H "Authorization: Bearer <your_token>" http://localhost:8080/api/topics
API Endpoints
Authentication
POST /api/auth/register - Register new user

POST /api/auth/login - Login user

Topics
GET /api/topics - Get all topics (paginated)

GET /api/topics/search?query=searchTerm - Search topics

POST /api/topics - Create new topic (authenticated)

PUT /api/topics/{id} - Update topic (owner/admin)

DELETE /api/topics/{id} - Delete topic (owner/admin)

Security Features
JWT-based authentication

Role-based access control

Password encryption with BCrypt

CSRF protection disabled for API

Stateless session management

Input validation with Jakarta Validation

Database Schema
The application uses three main tables:

users - User accounts and authentication

topics - Forum topics with problem/solution structure

comments - Comments on topics

Testing
Run tests with:

bash
mvn test
Production Considerations
Change JWT secret in production

Use environment variables for sensitive data

Enable HTTPS

Configure proper database connection pooling

Set up monitoring and logging

Implement rate limiting

Use database migrations (Flyway/Liquibase)

Contributing
Fork the repository

Create a feature branch

Commit your changes

Push to the branch

Create a Pull Request

License
MIT License - see LICENSE file for details

text

## Key Security Features Implemented:

1. **JWT Authentication** - Secure token-based authentication
2. **Role-Based Authorization** - Different access levels for users, moderators, and admins
3. **Password Encryption** - BCrypt password hashing
4. **Input Validation** - Jakarta Validation annotations
5. **SQL Injection Prevention** - Spring Data JPA with parameterized queries
6. **CORS Configuration** - Proper cross-origin settings
7. **CSRF Protection** - Disabled for API (stateless)
8. **Secure Headers** - Spring Security default security headers

This implementation follows all the best practices from the Alura courses and provides a solid foundation for a secure forum application.
