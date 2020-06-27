FROM openjdk:8
COPY ofo-service2.jar app.jar
VOLUME /tmp
EXPOSE 8013
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

