# Bookstore Management System

The Bookstore Management System is a Java-based web application built with Spring Boot. It provides a RESTful API for managing authors and books in a bookstore.

## Features

- Get a list of all authors
- Get an author by ID
- Add a new author
- Get a list of all books
- Get a lift of all books of certain author
- Add a new book
- Delete a book `etc`.

## Technologies Used

- Java 17
- Spring Boot
- Spring JDBC
- PostgreSQL
- Flyway (for database migrations)
- Docker
- Maven

## Prerequisites

Before running the application, make sure you have the following installed:

- Java Development Kit (JDK) 17 or higher
- PostgreSQL database
- Docker
- Maven

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/d3nnyyy/BookstoreManagementSystem.git
```
  
2. Open the project in your preffered IDE.

3. Run the Docker:

```bash
docker-compose up -d
```

4. Run the application:

```
mvn spring-boot:run
```

The application will be accessible at `http://localhost:8080`.

## API Endpoints

The following API endpoints are available:

### Authors

- `GET /authors`: Get all authors
- `GET /authors/{id}`: Get an author by ID
- `POST /authors`: Add a new author
- `DELETE /authors/{id}`: Delete an author
- `PUT /authors/{id}`: Update author information

### Books

- `GET /books`: Get all books
- `GET /books/{id}`: Get a book by ID
- `GET /books/author/{id}`: Get a book by author ID
- `POST /books`: Add a new book
- `DELETE /books/{id}`: Delete a book
- `PUT /books/{id}`: Update book information

## Testing with Postman

You can test the API endpoints using a tool like [Postman](https://www.postman.com/). Simply import the provided Postman collection, which contains pre-configured requests for each endpoint. You can modify the requests as needed or create new ones to suit your testing requirements.

To get started:

1. Install [Postman](https://www.postman.com/) if you haven't already.
2. Import the Postman collection file (`bookstore_postman.json`) included in this repository.
3. Update the base URL in Postman to match the server where the API is running.
4. Send requests to the desired API endpoints and review the responses.

Feel free to explore the different endpoints and experiment with different request payloads to interact with the API.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## Contact

For any inquiries or questions, please contact [d.tsebulia@gmail.com].
