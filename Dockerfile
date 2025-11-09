FROM alpine/java:21-jre

EXPOSE 8080:8080

WORKDIR /app

COPY build/libs/blokje-om-api.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
