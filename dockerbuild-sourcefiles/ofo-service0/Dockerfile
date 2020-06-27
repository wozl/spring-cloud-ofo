FROM openjdk:8
COPY ofo-service0.jar app.jar
VOLUME /tmp
EXPOSE 8011
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
