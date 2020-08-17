FROM openjdk:8
ADD target/football-0.0.1-SNAPSHOT.jar football-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java" ,"-jar" ,"football-0.0.1-SNAPSHOT.jar"]