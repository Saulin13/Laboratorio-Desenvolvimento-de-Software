# Use an official Ubuntu base image
FROM ubuntu:latest AS build

# Update packages and install OpenJDK
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

# Copy source code to the image
COPY . .

# Install Maven and run it to build the project
RUN apt-get install maven -y
RUN mvn clean install

# Start a new stage from a smaller JDK image to reduce size
FROM openjdk:21-jdk-slim

# Expose port 8080 for the application
EXPOSE 8080

# Copy the built JAR file from the previous stage
COPY --from=build target/todo-0.0.1-SNAPSHOT.jar app.jar

# Set the default command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]