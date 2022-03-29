FROM openjdk:8-jdk-alpine
MAINTAINER retoTipoCambio_HugoQ
RUN addgroup -S spring && adduser -S spring -G spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cjava-0.0.1-cjava.jar
ENTRYPOINT ["java","-jar","/cjava-0.0.1-cjava.jar"]