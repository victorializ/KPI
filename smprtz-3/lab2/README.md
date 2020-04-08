**Необхідно для запуску додатку:**  
docker  
docker-compose

**Для того, щоб запустити:**
cd KPI/smprtz-3/lab2 // перейти в директорію проекту  
sudo docker-compose up --scale ticket-service=2 // запустити (два екземпляри ticket-service)

Eureka Server URL: http://localhost:8761  
Service URL (instance 1): http://localhost:8081
Service URL (instance 2): http://localhost:8082
Api Gateway URL: http://localhost:8080
