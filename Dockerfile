FROM maven:3.6.1-jdk-11-slim AS build

RUN apt update && apt install net-tools

WORKDIR /java-app

COPY ./backend/ .

RUN mvn clean package -DskipTests

FROM openjdk:11 AS compiled

COPY --from=build /java-app/target/api-1.0.0-SNAPSHOT.jar /app/

COPY --from=build /java-app/src/main/resources/snoweeapi.p12 /app/

WORKDIR /app

EXPOSE 8080

CMD java -jar api-1.0.0-SNAPSHOT.jar
