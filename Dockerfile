FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/training-0.0.1-SNAPSHOT.jar my-spring-app.jar

EXPOSE 8443

ENTRYPOINT ["java", "-jar", "my-spring-app.jar"]