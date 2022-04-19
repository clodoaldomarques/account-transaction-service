# Account Transaction Service

## About
System for technical evaluation in a selection process

## Technologies ##

* Java 11.0.4
* Kotlin 1.6.20
* Springboot 2.6.6
* Postgresql 14.2-1

## Requirements ##

* IntelliJ IDEA 2022.1 (Community Edition) or latest
* openjdk 11 or latest
* Docker 20.10.14 or latest
* Docker-compose 1.29.2 or latest

## Database Access:##
- host: localhost
- port: 5432
- user: postgres
- password: pgadmin

## Tests ##
Running tests in command line:
```
$ ./gradlew check
```

## Running Service ##
```
$ docker-compose up -d
``` 

## Architecture ##
- https://netflixtechblog.com/ready-for-changes-with-hexagonal-architecture-b315ec967749

### Links to the Service: ###
- http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
