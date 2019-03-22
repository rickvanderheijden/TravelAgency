FROM mysql
ENV MYSQL_DATABASE=travel-agency
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=user
RUN mysql -h localhost -P 3306 --protocol=tcp -u root

FROM maven:3.6.0-jdk-8
COPY . /home/travelagency
WORKDIR /home/travelagency
RUN mvn -Dmaven.test.skip=true package
RUN cp ./target/BackEnd-0.0.1-SNAPSHOT.jar travelagency.jar
ENTRYPOINT ["java","-jar","travelagency.jar"]
