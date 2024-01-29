# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY out/artifacts/ri8_jar/ri8.jar /app/app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Define environment variables
ENV SPRING_PROFILES_ACTIVE production

# Run the application when the container launches
CMD ["java", "-jar", "app.jar"]
