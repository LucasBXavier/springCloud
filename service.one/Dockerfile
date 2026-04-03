FROM maven:3.9.6-amazoncorretto-21-debian as build
LABEL authors="Lucas"

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install -DskipTests

FROM amazoncorretto:21

COPY --from=build /app/target/service.tasks-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar

WORKDIR /app

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]