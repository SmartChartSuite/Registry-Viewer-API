#Build the Maven project
FROM maven:3.6.3-openjdk-17 as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install

FROM openjdk:17-jdk

COPY --from=builder /usr/src/app/target/registry-viewer-api-1.7.0.jar registry-viewer-api.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/registry-viewer-api.jar"]
