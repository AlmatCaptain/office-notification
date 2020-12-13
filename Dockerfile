FROM openjdk:8

ADD /target/office-notification-0.0.1-SNAPSHOT.jar office-notification-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "office-notification-0.0.1-SNAPSHOT.jar"]

EXPOSE 8090