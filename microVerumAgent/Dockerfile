#docker run --name micro-verum-log -p 127.0.0.1:27017:27017/tcp -d mongo
FROM openjdk:8-jdk

ARG service=localhost
ARG port=80


COPY . /usr/src/microVerumAgent

WORKDIR /usr/src/microVerumAgent

#RUN ./mvnw spring-boot:run