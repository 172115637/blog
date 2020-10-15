FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
#注意需要增加-Dspring.profiles.active=docker，来制定docker部署模式下的配置信息
ENTRYPOINT ["java","-jar","/app.jar"]