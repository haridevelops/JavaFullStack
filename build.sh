cd MovieCruiserAuthenticationService
source ./env-variable.sh
mvn clean package
docker build -t user-app .
cd ..
cd MovieCruiserApp
source ./env-variable.sh
mvn clean package
docker build -t movie-app .
cd ..
