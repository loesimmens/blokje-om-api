FROM ubuntu-latest
EXPOSE 8080:8080
RUN mkdir /app
WORKDIR /app
RUN .gradlew installDist --stacktrace
WORKDIR /app/build/install/app
CMD ["./blokje-om-api"]
