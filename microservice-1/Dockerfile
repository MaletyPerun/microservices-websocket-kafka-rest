FROM openjdk:17
COPY build/libs/microservice-1-0.0.1-SNAPSHOT.jar /microservice-1.jar
RUN apt-get update && apt-get install -y vim
CMD ["java", "-jar", "/microservice-1.jar"]