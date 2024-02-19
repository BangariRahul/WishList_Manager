# Wishlist Management System

The Wishlist Management System is a Spring Boot-based RESTful service designed to manage user wishlists, allowing users to create, view, and delete items from their wishlist. It's built with robust security features for user authentication and authorization, ensuring that wishlists are personal and secure.

## Features

- **User Authentication**: Securely register and log in users.
- **Wishlist Management**: Users can add items to their wishlist, view their wishlist, and delete items.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17
- Maven
- MySQL (or any other relational database you prefer)

### Installing

1. Clone the repository to your local machine:

2. Navigate into the project directory:

4. Build the project using Maven:

Run the Spring Boot application:

The service should now be up and running on localhost:8080.

## API Endpoints
The service exposes the following RESTful endpoints:

### User Authentication

POST /auth/create - Register a new user.

POST /auth/login - Authenticate a user and return a JWT token.

### Wishlist Management

POST /api/wishList/add - Add an item to the user's wishlist.

GET /api/wishList/get - Retrieve the user's wishlist.

DELETE /api/wishList/delete/{id} - Delete an item from the user's wishlist by item ID.

## Built With
Spring Boot - The web framework used

Maven - Dependency Management

JWT - Used for securely transmitting information between parties as a JSON object
