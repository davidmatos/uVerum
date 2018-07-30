#docker run --name micro-verum-log -p 127.0.0.1:27017:27017/tcp -d mongo
FROM openjdk:8-jdk

COPY . /usr/src/microVerumAdminConsole

WORKDIR /usr/src/microVerumAdminConsole

#RUN ./mvnw spring-boot:run