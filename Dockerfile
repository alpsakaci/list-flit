FROM openjdk:16-jdk-alpine
WORKDIR /usr/src/app
COPY . .
RUN ./mvnw package
ENTRYPOINT ["./mvnw", "spring-boot:run"]

