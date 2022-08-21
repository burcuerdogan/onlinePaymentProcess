FROM openjdk:13-jdk-alpine
ADD target/onlinePaymentProcess-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]