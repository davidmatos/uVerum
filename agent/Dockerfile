#running an image of mongo
#docker run --name microverum-db -p 127.0.0.1:27017:27017/tcp -d mongo
 
# Run from the parent folder
# docker build -t agent -f agent/Dockerfile .
FROM maven

COPY ./common /usr/src/common
RUN cd /usr/src/common && mvn install

COPY ./agent /usr/src/agent

RUN cd /usr/src/agent && mvn install

WORKDIR /usr/src/agent

#docker run agent /bin/bash -c "mvn exec:java"