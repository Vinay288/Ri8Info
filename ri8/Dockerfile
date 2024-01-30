# Use an official Gradle image as a build stage
FROM gradle:8.5.0-jdk17 AS build

# Set the working directory to /app
WORKDIR ri8/

# Copy the build.gradle and settings.gradle files to the container
COPY build.gradle settings.gradle ./

# Copy the src folder to the container
COPY src src

# Build the application
RUN gradle build -x test

# Use an official OpenJDK runtime as the production image

FROM openjdk:17-jdk-slim


# Set the working directory to /app
WORKDIR /app

RUN ls -l app/

# Copy the JAR file from the build stage to the production image

# Expose the port that the application will run on
EXPOSE 8080

# Define environment variables
ENV SPRING_PROFILES_ACTIVE production

# Run the application when the container launches
CMD ["java", "-jar", "app.jar"]
