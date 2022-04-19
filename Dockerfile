FROM gradle:7.4.2-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11
MAINTAINER io.pismo
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/account-transaction-service-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-jar","./app/app.jar"]