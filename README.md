# Darasa AI - Educational Platform Backend

Darasa AI is a robust backend system for educational platforms, built with Spring Boot. It provides a comprehensive API for managing courses, students, teachers, assignments, and more.

## Features

- User Management (Students, Teachers, Administrators)
- Course and Offering Management
- Assignment Handling
- Timed Tests
- Note Taking System
- JWT-based Authentication
- Role-Based Access Control

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- JWT Authentication

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher
- Git

## Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/darasa-ai.git
   cd darasa-ai
   ```

2. **Configure Environment Variables**
   
   Create a `.env` file in the root directory with the following variables:
   ```properties
   DB_URL=jdbc:postgresql://localhost:5432/your_database_name
   DB_USER=your_database_user
   DB_PASSWORD=your_database_password
   JWT_SECRET=your_jwt_secret_key
   SIGNUP_CODE=your_signup_code
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The server will start on `http://localhost:8080`

## 🔑 API Authentication

The API uses JWT tokens for authentication. Include the token in the Authorization header:
