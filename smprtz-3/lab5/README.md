Prerequisites:  
docker  
docker-compose up   
  
To run:  
cd lab5     
docker-compose up --scale consumer=3   
  
Eureka Server URL: http://localhost:8761  
Service URL: http://localhost:8081  
Api-gateway: http://localhost:8080 

Для того, щоб побачити логи consumer:  
sudo docker logs -f [container-id]