FROM java:8-jdk
RUN mkdir /app
WORKDIR /app
COPY target/config-service-1.0-SNAPSHOT-application.jar /app
VOLUME /app/config
ENTRYPOINT ["java", "-jar", "/app/config-service-1.0-SNAPSHOT-application.jar"]
EXPOSE 8100