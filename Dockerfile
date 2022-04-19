FROM openjdk:11
MAINTAINER io.pismo
COPY ./build/libs/account-transaction-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","./app.jar"]