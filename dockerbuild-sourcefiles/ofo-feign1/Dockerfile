FROM openjdk:8
COPY ofo-feign1.jar app.jar
VOLUME /tmp
EXPOSE 8022
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
