Необхідно для запуску додатку:

docker  
docker-compose

cd smprtz-3/lab3 // перейти в директорію проекту  
docker-compose up // запустити за допомогою docker-compose  

Eureka Server URL: http://localhost:8761  
Api Gateway URL: http://localhost:8080  
Service URL (instance 1): http://localhost:8081  
Config Server URL: http://localhost:8888  

папка із кофігураціями https://github.com/youngdecadent/config  
для того щоб змінити конфігурації:  
git clone https://github.com/youngdecadent/config.git  
cd config  
echo test.prop1=1 > application.properties // змінити prop1 на 1  
git add .  
git commit -m "change config"  
git push origin master  