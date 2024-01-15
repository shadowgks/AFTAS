FROM eclipse-temurin:17-jdk-alpine
MAINTAINER "saadmoumou"
ADD target/AppGCM-0.0.1-SNAPSHOT.jar AppGCM_backend_v1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","AppGCM_backend_v1.jar"]