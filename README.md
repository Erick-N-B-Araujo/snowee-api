# Snowee API

Welcome to the Snowee API repository. This project serves as the backend API for Snowee Game Corp, developed using Spring Boot and Java.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Configuration](#configuration)
- [Testing](#testing)
- [Deployment](#deployment)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Project Overview

The Snowee API is designed to provide robust backend services for Snowee Game Corp's applications. Built with Spring Boot, it ensures scalable and efficient handling of game-related data and operations.

## Features

- **RESTful Endpoints**: Provides a comprehensive set of APIs for game functionalities.
- **Database Integration**: Utilizes PostgreSQL for data persistence.
- **Security**: Implements authentication and authorization mechanisms.
- **Docker Support**: Contains Docker configurations for containerized deployment.

## Getting Started

Follow these instructions to set up and run the project locally.

### Prerequisites

Ensure you have the following installed:

- [Java 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/) (optional, for containerized deployment)

### Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/Erick-N-B-Araujo/snowee-api.git
   cd snowee-api
   ```

2. **Configure the Database**:

   1. Create a PostgreSQL database named `snowee_db`.
   2. Update the database configuration in `src/main/resources/application.properties` with your PostgreSQL credentials.

3. **Build the Application**:

   ```bash
   mvn clean install
   ```

   ### Running the Application

   To start the application:

   ```bash
   java -jar target/snowee-api-0.0.1-SNAPSHOT.jar
   ```

   The API will be accessible at `http://localhost:8080/`.

   ## Configuration

   Configuration files are located in the `src/main/resources` directory. Adjust settings as needed for your development or production environment.

   ## Testing

   To run tests:

   ```bash
   mvn test
   ```

   ## Deployment

   The project includes a `Dockerfile` and `docker-compose.yml` for containerized deployment. To build and run the Docker container:

   ```bash
   docker-compose up --build
   ```

   Ensure you have Docker installed and running on your system.

   ## Contributing

   Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure that your code adheres to the project's coding standards and includes appropriate tests.

   ## License

   This project is licensed under the Apache-2.0 License. See the LICENSE file for details.
