# Dockerized Microservices Setup

## Overview
This project contains a Dockerized setup for a Spring Boot application, a Node.js application, and a PostgreSQL database with practical demonstration in containerization techniques

## Running Containers Independently


### Spring Boot and NodeJSApplication
To build and run the Spring Boot container separately:

```sh
docker build -t spring-app -f dockerfile.java .
docker run --name dockerized_spring   --env SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/microservices_db   --env SPRING_DATASOURCE_USERNAME=user   --env SPRING_DATASOURCE_PASSWORD=password   -p 8080:8080 spring-app
```

To build and run the Node.js container separately:

```sh
docker build -t node-app -f dockerfile.node .
docker run --name dockerized_node -p 8081:8081 node-app
```

## Running with Docker Compose

To run the entire setup using Docker Compose, execute:

```sh
docker-compose up -d
```

This command will start PostgreSQL, the Spring Boot app, and the Node.js app in detached mode.

To stop all containers, use:

```sh
docker-compose down
```

## Available Endpoints

- **Spring Boot API:** [`http://localhost:8080/java/api/v1/status`](http://localhost:8080/java/api/v1/status)
- **Node.js API:** [`http://localhost:8081/node/api/v1/result`](http://localhost:8081/node/api/v1/result)
- **Node.js FROM SPRING:** [`http://localhost:8080/java/api/v1/node`](http://localhost:8080/java/api/v1/node)

## Notes

- Ensure that the ports `5432`, `8080`, and `8081` are free before running the containers or remap them in docker-compose file if they are in use
- The database is initialized with the credentials defined in the `docker-compose.yml` 
