FROM openjdk:8-jdk-alpine as build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests

ADD target/central-erros-0.0.1-SNAPSHOT.jar central-erros-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar","central-erros-0.0.1-SNAPSHOT.jar"]