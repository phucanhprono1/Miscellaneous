ARG image_name=míscellaneous

FROM openjdk:17
EXPOSE 8081
ADD build/libs/Miscellaneous-0.0.1-SNAPSHOT.jar Miscellaneous-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/Miscellaneous-0.0.1-SNAPSHOT.jar"]

LABEL image.name=$image_name


#cd api_gateway
#docker network create ducapu-network(optional)
#docker build --build-arg image_name=my-api-gateway-image -t my-api-gateway-image .
#docker stop api-gateway
#docker rm api-gateway
#docker run -p 8888:8888 --network ducapu-network --name api-gateway my-api-gateway-image