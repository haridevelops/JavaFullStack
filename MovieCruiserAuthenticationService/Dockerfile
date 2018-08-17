FROM java:8-jre
WORKDIR /home/ubuntu/Documents/docker
ENV MYSQL_DATABASE=moviedb
ENV MYSQL_USER=app_root
ENV MYSQL_PASSWORD=root123
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviedb
ADD ./target/MovieCruiserAuthenticationService-0.0.1-SNAPSHOT.jar /home/ubuntu/Documents/docker/MovieCruiserAuthenticationService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","MovieCruiserAuthenticationService-0.0.1-SNAPSHOT.jar"]
