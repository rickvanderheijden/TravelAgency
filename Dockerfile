FROM mariadb
EXPOSE 3306
ENV MYSQL_DATABASE=travel-agency
ENV MYSQL_ALLOW_EMPTY_PASSWORD=yes
#ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=user
#RUN mariadb -h localhost --protocol=tcp -uroot
#RUN mysql

RUN mysqld_safe

FROM maven:3.6.0-jdk-8
COPY . /home/travelagency
WORKDIR /home/travelagency
RUN mvn -Dmaven.test.skip=true package
RUN cp ./target/BackEnd-0.0.1-SNAPSHOT.jar travelagency.jar
ENTRYPOINT ["java","-jar","travelagency.jar"]
