docker-compose down

mvn clean install -T 1C -am -o \
    -pl management-service/management-service-impl

docker build -t "dms/management-service:latest" management-service/management-service-impl

docker-compose up                  