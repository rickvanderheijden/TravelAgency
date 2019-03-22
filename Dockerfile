FROM maven:3.6.0-jdk-8
RUN mvn -f /pom.xml -Dmaven.test.skip=true package
RUN ls
ADD /target/travelagency.jar travelagency.jar
ENTRYPOINT ["java","-jar","travelagency.jar"]
