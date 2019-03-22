FROM maven:3.6.0-jdk-8

COPY . /home/travelagency
WORKDIR /home/travelagency

RUN find . -name "pom.xml"
RUN mvn -Dmaven.test.skip=true package

ADD /target/travelagency.jar travelagency.jar

ENTRYPOINT ["java","-jar","travelagency.jar"]
