# Use OpenJDK 22
FROM openjdk:22-jdk

# Set working directory
WORKDIR /app

# Copy JAR file

COPY build/libs/*.jar NutriTrack_Server.jar
ENTRYPOINT ["java", "-jar", "/app/NutriTrack_Server.jar"]