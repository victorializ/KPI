#!/bin/sh
echo get config
curl -X GET http://localhost:8080/config
echo
echo change config
cd configdir
echo test.firstprop=1 > application.properties 
echo test.secondprop=2 >> application.properties 
echo test.thirdprop=3 > spring-cloud-eureka-feign-client.properties
echo test.fourthprop=4 >> spring-cloud-eureka-feign-client.properties
echo test.fifthprop=5 > ticket-service.properties
echo test.sixthprop=6 >> ticket-service.properties
git status
git add .
git commit -m "commit"
cat application.properties
echo
cat spring-cloud-eureka-feign-client.properties
echo
cat ticket-service.properties
echo
echo refresh 
curl localhost:8080/actuator/refresh -d {} -H "Content-Type: application/json"
curl localhost:46843/actuator/refresh -d {} -H "Content-Type: application/json"
echo
echo get config
curl -X GET http://localhost:8080/config
echo
