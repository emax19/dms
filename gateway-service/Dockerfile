FROM java:8-jdk

ENV DOCKERIZE_VERSION v0.2.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

RUN mkdir /app
WORKDIR /app
COPY target/gateway-service-1.0-SNAPSHOT-application.jar /app
ENTRYPOINT ["dockerize", "-timeout", "5m", "-wait", "tcp://config-service:8100", "-wait", "tcp://discovery-service:8761", "java", "-jar", "/app/gateway-service-1.0-SNAPSHOT-application.jar",  "--spring.profiles.active=docker"]
EXPOSE 8761