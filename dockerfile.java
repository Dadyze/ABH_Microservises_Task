# Dockerfile for Spring Boot App

## spring-boot-app/Dockerfile
FROM maven AS builder

# Clone repository
WORKDIR /app
RUN git clone --depth 1 https://github.com/kkenan/basic-microservices && \
    mv basic-microservices/spring-boot-app ./ && \
    rm -rf basic-microservices

# Build the Spring Boot app
WORKDIR /app/spring-boot-app
RUN mvn clean package

# Create final runtime image
FROM openjdk:8
WORKDIR /app
COPY --from=builder /app/spring-boot-app/target/spring-boot-app-*.jar app.jar
EXPOSE 8080
ENV APP_NODE_URL=http://node-app:8081/
CMD ["java", "-jar", "app.jar"]
