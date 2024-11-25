# Use the official OpenJDK image as a base image
FROM openjdk:17-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and other necessary files
COPY pom.xml .
COPY src ./src

# Build the application using Maven inside the Docker container
RUN apt-get update && apt-get install -y maven && \
    mvn clean install -DskipTests

# Now copy the JAR file created by Maven (assuming it is located in the target directory)
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file from the build stage to the final stage
COPY --from=build /app/target/training-0.0.1-SNAPSHOT.jar /app/my-spring-app.jar

# Expose the port your app will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "my-spring-app.jar"]