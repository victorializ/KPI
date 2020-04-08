Необхідно для запуску додатку:

docker  
docker-compose

cd smprtz-3/lab2 // перейти в директорію проекту  
sudo docker-compose up --scale ticket-service=2 // запустити (два екземпляри ticket-service)

Eureka Server URL: http://localhost:8761  
Api Gateway URL: http://localhost:8080  
