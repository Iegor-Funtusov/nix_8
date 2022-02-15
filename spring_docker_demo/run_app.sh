#!/bin/sh

#docker pull mysql/mysql-server:latest
docker stop mysql_docker_demo_container
#docker rm mysql_docker_demo_container
docker network rm mysql_docker_demo
#docker network create mysql_docker_demo
#docker container run --name mysql_docker_demo_container --network mysql_docker_demo --network-alias mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=nix_8_db -d mysql/mysql-server:latest

mvn clean install

docker stop spring_docker_demo_container

docker rm spring_docker_demo_container

#docker image rm spring_docker_demo

#docker build -t spring_docker_demo .

#docker container run --name spring_docker_demo_container --network mysql_docker_demo -p 8082:8085 -d spring_docker_demo