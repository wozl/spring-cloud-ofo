FROM openjdk:8
COPY ofo-gateway1.jar app.jar
VOLUME /tmp
EXPOSE 7611
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
