FROM gradle:7.5.0-jdk17-focal AS builder
WORKDIR /app
COPY . .
RUN SPRING_PROFILES_ACTIVE="deploy" gradle build

FROM amazoncorretto:17.0.3
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/build/libs/catto.jar server.jar
CMD ["java", "-jar", "server.jar"]
