FROM openjdk:8
COPY ofo-gateway2.jar app.jar
VOLUME /tmp
EXPOSE 7612
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
