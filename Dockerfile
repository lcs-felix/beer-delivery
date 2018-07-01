FROM 11-jre-slim

MAINTAINER Lucas Felix <lfelixsampaio@gmail.com>

EXPOSE 8080

ADD target/beer-delivery-1.0.jar /usr/share/beer-delivery/beer-delivery-1.0.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/beer-delivery/beer-delivery-1.0.jar"]
