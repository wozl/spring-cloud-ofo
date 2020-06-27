FROM openjdk:8
COPY ofo-gateway0.jar app.jar
VOLUME /tmp
EXPOSE 7610
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

