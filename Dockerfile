FROM 3.6.0-jdk-8
RUN mvn -Dmaven.test.skip=true package
ADD /target/travelagency.jar travelagency.jar
ENTRYPOINT ["java","-jar","travelagency.jar"]
