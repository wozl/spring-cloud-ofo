FROM openjdk:8
COPY ofo-hystrix-dashboard-turbine1.jar app.jar
VOLUME /tmp
EXPOSE 9002
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
