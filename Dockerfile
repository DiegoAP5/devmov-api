FROM amazoncorretto:17-alpine3.16
ADD target/devmov-0.0.1-SNAPSHOT.jar devmov-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "devmov-0.0.1-SNAPSHOT.jar"]