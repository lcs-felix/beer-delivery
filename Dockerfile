FROM openjdk:10.0-jre-slim

MAINTAINER Lucas Felix <lfelixsampaio@gmail.com>

EXPOSE 8080

ARG JAR_FILE

ADD target/${JAR_FILE} /usr/share/beer-delivery/beer-delivery.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/beer-delivery/beer-delivery.jar"]
