# Filename: Dockerfile
FROM azul/zulu-openjdk-alpine:17-jre
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]