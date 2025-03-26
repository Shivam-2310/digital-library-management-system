# Library Book Management System

## Overview

Library Book Management System is a robust Spring Boot application designed to manage digital library book inventories efficiently. The system provides comprehensive CRUD operations for managing books, with features like book addition, retrieval, updating, and deletion.

## 🚀 Technology Stack

- **Language**: Java 17
- **Framework**: Spring Boot 3.4.4
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **ORM**: Spring Data JPA
- **Dependencies**: Lombok, Spring Web

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- Java Development Kit (JDK) 17
- Maven
- PostgreSQL
- Git (optional)

## 🔧 Database Setup

1. Install PostgreSQL
2. Create a new database:
```sql
CREATE DATABASE digital_library;
```

## 🛠️ Installation Steps

### 1. Clone the Repository
```bash
git clone https://github.com/Shivam-2310/digital-library-management-system.git
cd library-book-management-system
```

### 2. Configure Database Connection

Open `src/main/resources/application.properties` and update database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/digital_library
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build the Project
```bash
./mvnw clean install
```

## 🚀 Running the Application

### Using Maven
```bash
./mvnw spring-boot:run
```

### Using IDE
- Open the project in IntelliJ IDEA or Eclipse
- Run `LibraryBookManagementSystemApplication.java`

## 📡 API Endpoints

### Book Management Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/books` | Add a new book |
| GET | `/api/books` | Retrieve all books |
| GET | `/api/books/{bookId}` | Get book by ID |
| GET | `/api/books/search?title=` | Search book by title |
| PUT | `/api/books/{bookId}` | Update a book |
| DELETE | `/api/books/{bookId}` | Delete a book |

## 📝 Sample Book Payload
```json
{
    "bookId": "BOOK001",
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "genre": "Programming",
    "availabilityStatus": "AVAILABLE"
}
```

## 🧪 Testing

Run automated tests:
```bash
./mvnw test
```

## 🔒 Error Handling

The application provides robust error handling:
- Custom `BookNotFoundException`
- Global exception handling
- Descriptive error messages

