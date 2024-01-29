# Use an official Gradle image as a build stage
FROM gradle:7.2.0-jdk11 AS build

# Set the working directory to /app
WORKDIR /app

# Copy the build.gradle and settings.gradle files to the container
COPY build.gradle settings.gradle ./

# Copy the src folder to the container
COPY src src

# Build the application
RUN gradle build -x test

# Use an official OpenJDK runtime as the production image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file from the build stage to the production image
COPY --from=build /app/build/libs/ri8.jar /app/app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Define environment variables


# Run the application when the container launches
CMD ["java", "-jar", "app.jar"]
