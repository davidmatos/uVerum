#running an image of mongo
#docker run --name microverum-db -p 127.0.0.1:27017:27017/tcp -d mongo

# Run from the parent folder
# sudo docker build -t teste -f microVerumAdminConsole/Dockerfile .

#FROM openjdk:8-jdk

#RUN apt-get update && apt-get -y install maven

FROM maven


COPY ./microVerumCommonLibs /usr/src/microVerumCommonLibs
RUN cd /usr/src/microVerumCommonLibs && mvn install


COPY ./microVerumAgent /usr/src/microVerumAgent
WORKDIR /usr/src/microVerumAgent


#sudo docker run teste /bin/bash -c "./mvnw spring-boot:run"