FROM java:8
EXPOSE 8080
ADD /target/travelagency.jar travelagency.jar
ENTRYPOINT ["java","-jar","travelagency.jar"]
