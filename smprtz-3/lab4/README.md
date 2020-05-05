**Необхідно для запуску додатку**:  
docker  
docker-compose  
порти 8761, 8080, 8082, 8083, 8888 вільні на момент запуску

**Для того щоб запустити**:  
cd KPI/smprtz-3/lab3 // перейти в директорію проекту  
docker-compose up // запустити за допомогою docker-compose  

**Для того щоб змінити конфігурації**:  
sudo docker exec -it [configserver_container_id] /bin/sh  
// або  
sudo docker exec -it  [configserver_container_id] /bin/bash  
cd config  
echo test.prop1=1 > application.properties  
echo test.prop2=2 >> application.properties  
// або змінити файли за допомогою редактора “vi”  
git add .  
git commit -m "change config" 

Eureka Server URL: http://localhost:8761  
Api Gateway URL: http://localhost:8080  
Service URL (instance 1): http://localhost:8082   
Service URL (instance 2): http://localhost:8083    
Config Server URL: http://localhost:8888  
