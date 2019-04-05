FROM maven:3.6.0-jdk-8
MAINTAINER koen sengers

COPY . /home/travelagency
WORKDIR /home/travelagency
RUN cp ./src/main/resources/application-docker.properties ./src/main/resources/application.properties
RUN mvn -Dmaven.test.skip=true package
RUN cp ./target/BackEnd-0.0.1-SNAPSHOT.jar travelagency.jar

EXPOSE 9000
ENTRYPOINT ["java","-jar","travelagency.jar", "0.0.0.0"]
