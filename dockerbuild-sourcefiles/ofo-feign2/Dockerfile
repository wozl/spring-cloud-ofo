FROM openjdk:8
COPY ofo-feign2.jar app.jar
VOLUME /tmp
EXPOSE 8023
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

