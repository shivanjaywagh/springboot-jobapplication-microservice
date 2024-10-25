# Stage 1: Build the application
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17
VOLUME /tmp
# Update the JAR file path if it has a different name
COPY --from=build /app/target/firstjobapp-0.0.1-SNAPSHOT.jar my-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/my-app.jar"]
