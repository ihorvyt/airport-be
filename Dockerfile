FROM ubuntu:latest
LABEL authors="vlakk"
RUN apt-get update
RUN apt-get intsall openjdk-17-jdk -y
COPY . .
RUN ./gradlew bootJar --no-daemon
EXPOSE 8080
COPY --from=build /build/libs/demo-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]