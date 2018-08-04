FROM java:8-jre
WORKDIR /home/ubuntu/Documents/docker
ENV MYSQL_DATABASE=moviedb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviedb
ADD ./target/MovieCruiserApp-0.0.1-SNAPSHOT.jar /home/ubuntu/Documents/docker/MovieCruiserApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","MovieCruiserApp-0.0.1-SNAPSHOT.jar"]
