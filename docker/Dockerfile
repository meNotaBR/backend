FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY .. /app

RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/exchanger-0.0.1-SNAPSHOT.jar /app/exchanger-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "exchanger-0.0.1-SNAPSHOT.jar"]