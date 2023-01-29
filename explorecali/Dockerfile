FROM openjdk:18
WORKDIR /
ADD target/explorecali-0.0.1-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT [ "java", "-Dspring.profiles.active=docker", "-jar", "/explorecali-0.0.1-SNAPSHOT.jar"]
