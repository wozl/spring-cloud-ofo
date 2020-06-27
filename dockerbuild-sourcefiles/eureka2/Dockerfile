FROM openjdk:8
COPY ofo-eureka-server-8083.jar app.jar
VOLUME /tmp
EXPOSE 8083
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
