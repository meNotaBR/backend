FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY . /app

RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:21-jdk-slim

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]