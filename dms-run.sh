docker-compose down

mvn clean install -T 1C -am -o \
    -pl config-service \
    -pl discovery-service \
    -pl gateway-service \
    -pl management-service/management-service-impl

docker build -t "dms/config-service:latest" config-service
docker build -t "dms/discovery-service:latest" discovery-service
docker build -t "dms/gateway-service:latest" gateway-service
docker build -t "dms/management-service:latest" management-service/management-service-impl

docker-compose up                  