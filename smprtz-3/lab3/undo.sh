#!/bin/sh
cd configdir
echo test.firstprop=first > application.properties 
echo test.secondprop=second >> application.properties 
echo test.thirdprop=third > spring-cloud-eureka-feign-client.properties
echo test.fourthprop=forth >> spring-cloud-eureka-feign-client.properties
echo test.fifthprop=fifth > ticket-service.properties
echo test.sixthprop=sixth >> ticket-service.properties
git status
git add .
git commit -m "commit"
curl localhost:8080/actuator/refresh -d {} -H "Content-Type: application/json"
curl localhost:46843/actuator/refresh -d {} -H "Content-Type: application/json"
curl -X GET http://localhost:8080/config

