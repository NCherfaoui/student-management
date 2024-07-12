# Student Management System

This project is a simple Student Management System built with Spring Boot and MongoDB. It provides basic CRUD operations
for managing student records.

## Technologies Used

- Java 21
- Spring Boot 3.3.1
- MongoDB
- Docker
- Maven
- JUnit 5
- Mockito

## Prerequisites

Before you begin, ensure you have met the following requirements:

- JDK 21 installed
- Maven installed
- Docker and Docker Compose installed
- Git installed

## Getting Started

To get this project up and running on your local machine, follow these steps:

1. Clone the repository:

```bash
git clone https://github.com/NCherfaoui/student-management.git
cd student-management
```

2. Start MongoDB using Docker Compose:
```bash

docker-compose up -d

```

3. Build the project:
```bash

mvn clean install

```

4. Run the application:
```bash

mvn spring-boot:run

```

The application will start and be available at http://localhost:8080.

## API Endpoints

- GET `/api/students`: Retrieve all students
- GET `/api/students/{id}`: Retrieve a specific student by ID
- POST `/api/students`: Create a new student
- PUT `/api/students/{id}`: Update an existing student
- DELETE `/api/students/{id}`: Delete a student

## Sample Request

To create a new student:

```http request

POST http://localhost:8080/api/students
Content-Type: application/json

{
"firstName": "John",
"lastName": "Doe",
"email": "john.doe@example.com",
"age": 20
}

```

## Running Tests

To run the unit tests, execute the following command:

```bash

mvn test

```

## Project Structure

- `src/main/java/com/nc/studentmanagement`: Contains the main application code
  - `controller`: REST controllers
  - `model`: Data models
  - `repository`: Database repositories
  - `service`: Business logic
  - `config`: Configuration classes
- `src/test/java/com/nc/studentmanagement`: Contains the test classes

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is open source and available under the [MIT License](LICENSE).
