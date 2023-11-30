#FROM alpine:latest
#RUN apk add --no-cache openjdk17
#COPY target/BookStoreApp-0.0.1-SNAPSHOT.jar /app/app.jar
#WORKDIR /
##RUN mv /app/*.jar /app/app/jar
#ENTRYPOINT ["java"]
#CMD ["-jar", "app.jar"]
##CMD echo "hello"
#
#
##FROM openjdk:17-jdk-alpine

##FROM openjdk:17
#FROM adoptopenjdk/openjdk17
#EXPOSE 8080
##ENV APPDIR /JAR_FILE #ENV container ile gorulur amma ARG gorunmur
##ADD ${APPDIR} application.jar
#ARG JAR_FILE=target/BookStoreApp-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} application.jar
#ENTRYPOINT ["java","-jar", "/application.jar"]

FROM openjdk:17-jdk-alpine
#FROM alpine:3.12
#RUN apk update && apk add openjdk17
LABEL "maintainer"="Javidan <cavidan.niyazali@gmail.com>"
LABEL "description"="BookStore Application"
RUN addgroup -S app && adduser -S app -G app
USER app
#WORKDIR /app
COPY target/BookStoreApp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker", "/app.jar"]
