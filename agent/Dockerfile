#running an image of mongo
#docker run --name microverum-db -p 127.0.0.1:27017:27017/tcp -d mongo

# Run from the parent folder
# docker build -t agent -f microVerumAgentProxy/Dockerfile .

#FROM openjdk:8-jdk
#RUN apt-get update && apt-get -y install maven
FROM maven


COPY ./microVerumCommonLibs /usr/src/microVerumCommonLibs
RUN cd /usr/src/microVerumCommonLibs && mvn install


COPY ./microVerumAgentProxy /usr/src/microVerumAgentProxy
WORKDIR /usr/src/microVerumAgentProxy


#docker run agent /bin/bash -c "mvn exec:java"