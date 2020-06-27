FROM openjdk:8
ADD ofo-eureka-server-8081.jar app.jar
VOLUME /tmp
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
