FROM openjdk:11-jdk
VOLUME /tmp
COPY target/web-scraping.jar web-scraping.jar
ENTRYPOINT ["java", "-jar", "/web-scraping.jar"]