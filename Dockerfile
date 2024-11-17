# Stage 1: Build the application
FROM ubuntu:latest AS build
LABEL authors="vlakk"

# Install dependencies
RUN apt-get update && apt-get install -y openjdk-17-jdk wget unzip

# Install Gradle
RUN wget https://services.gradle.org/distributions/gradle-7.5.1-bin.zip -P /tmp \
    && unzip -d /opt/gradle /tmp/gradle-7.5.1-bin.zip \
    && ln -s /opt/gradle/gradle-7.5.1/bin/gradle /usr/bin/gradle

# Set environment variables
ENV GRADLE_HOME=/opt/gradle/gradle-7.5.1
ENV PATH=${GRADLE_HOME}/bin:${PATH}

WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# Ensure the JAR file is created
RUN ls -l /app/build/libs/

# Stage 2: Create the Docker image
FROM openjdk:17-jdk-slim
LABEL authors="vlakk"

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
