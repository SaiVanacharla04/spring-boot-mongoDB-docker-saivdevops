# -------------------------------
# Stage 1: Build the application
# -------------------------------
FROM maven:3.8.5-openjdk-8-slim AS build
# Use Maven image with JDK 8 to build the Spring Boot application

# Set the working directory inside the build container
WORKDIR /app

# Copy only pom.xml first to leverage Docker layer caching
COPY pom.xml .

# Download all Maven dependencies (without compiling the source code)
RUN mvn dependency:go-offline -B

# Copy the complete application source code
COPY src ./src

# Build the application and generate the JAR file
# -DskipTests skips running unit tests to speed up the build
RUN mvn clean package -DskipTests

# -------------------------------
# Stage 2: Run the application
# -------------------------------
FROM openjdk:8-alpine
# Lightweight JDK image for running the application

# Define application home directory as an environment variable
ENV PROJECT_HOME=/opt/app

# Set the working directory for the runtime container
WORKDIR $PROJECT_HOME

# Copy the generated JAR file from the build stage
COPY --from=build /app/target/spring-boot-mongo-1.0.jar $PROJECT_HOME/spring-boot-mongo.jar

# Expose port 8080 (used by the Spring Boot application)
EXPOSE 8080

# Start the Spring Boot application
CMD ["java", "-jar", "spring-boot-mongo.jar"]
