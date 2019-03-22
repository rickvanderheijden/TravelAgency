FROM maven:3.6.0-jdk-8

COPY . /home/travelagency
WORKDIR /home/travelagency

RUN mvn -Dmaven.test.skip=true package
RUN cp ./target/BackEnd-0.0.1-SNAPSHOT.jar travelagency.jar

ENTRYPOINT ["java","-jar","travelagency.jar"]
