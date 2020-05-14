FROM maven:3.6.0-jdk-8-alpine AS maven
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
RUN mvn package

FROM openjdk:8
WORKDIR /app
COPY --from=maven app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]