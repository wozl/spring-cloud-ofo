FROM openjdk:8
COPY ofo-hystrix-dashboard-turbine0.jar app.jar
VOLUME /tmp
EXPOSE 9001
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

