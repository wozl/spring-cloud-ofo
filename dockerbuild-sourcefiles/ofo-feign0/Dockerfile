FROM openjdk:8
COPY ofo-feign0.jar app.jar
VOLUME /tmp
EXPOSE 8021
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

