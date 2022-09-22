#Build the Maven project
FROM maven:3.8.6 as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install

FROM openjdk:8-jdk-alpine
RUN apk update
RUN apk add zip

COPY --from=builder /usr/src/app/target/registry-viewer-api-1.1.0.jar registry-viewer-api.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/registry-viewer-api.jar"]
