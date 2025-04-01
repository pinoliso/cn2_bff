FROM openjdk:21-ea-24-oracle

WORKDIR /app
COPY target/bff-1.0-SNAPSHOT.jar app.jar

CMD [ "java", "-jar", "app.jar" ]