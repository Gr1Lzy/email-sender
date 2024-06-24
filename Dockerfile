FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/email-sender-0.0.1-SNAPSHOT.jar email-sender.jar

ENTRYPOINT ["java", "-jar", "email-sender.jar"]
