FROM openjdk:8
COPY ofo-service1.jar app.jar
VOLUME /tmp
EXPOSE 8012
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
