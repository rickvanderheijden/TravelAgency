FROM maven:3.6.0-jdk-8

COPY . /home/travelagency
WORKDIR /home/travelagency

RUN find . -name "pom.xml"
RUN mvn -Dmaven.test.skip=true package

RUN find . -name "BackEnd*.jar"

COPY ./home/travelagency/target/BackEnd-0.0.1-SNAPSHOT.jar travelagency.jar

ENTRYPOINT ["java","-jar","travelagency.jar"]
