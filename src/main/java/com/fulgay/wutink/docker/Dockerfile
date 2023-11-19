# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the packaged JAR file into the container
COPY Wutink-1.0-SNAPSHOT.jar  /app/app.jar

# Expose the port your application runs on (default is 8080)
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
