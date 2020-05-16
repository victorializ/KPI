Prerequisites:  
docker  
docker-compose up   
  
To run:  
cd lab4   
docker-compose up --scale video-game-client=2   
If there are any problems running this command, chances are it's due to network connection. Try running it again.  
  
Eureka Server URL: http://localhost:8761  
Service URL (instance 1): http://localhost:8081  
Service URL (instance 2): http://localhost:8082  
Api-gateway: http://localhost:8080  
Grafana UI(username/password:admin/admin): http://localhost:3000  
To get valid response, add /games to any service or api-gateway URL, ie http://localhost:8080/games  
