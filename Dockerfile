FROM eclipse-temurin:17-jdk-alpine
MAINTAINER  pascalngongang@gmail.com
CMD mwn clean package
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]