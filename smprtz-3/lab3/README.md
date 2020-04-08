**Необхідно для запуску додатку**:  
docker  
docker-compose  
порти 8761, 8080, 8082, 8083, 8888 вільні на момент запуску

**Для того щоб запустити**:   
cd smprtz-3/lab3 // перейти в директорію проекту  
docker-compose up // запустити за допомогою docker-compose  

**Репозиторій із кофігураціями** https://github.com/youngdecadent/config 

**Для того щоб змінити конфігурації**:  
git clone https://github.com/youngdecadent/config.git  
cd config  
echo test.prop1=1 > application.properties // змінити prop1 на 1  
git add .  
git commit -m "change config"  
git push origin master  

**Тестові запити** в файлі test.http  
сервіси можуть запуститися до config серверу, в цьому випадку, властивості конфігурацій отримають дефолтні значення
для того, щоб отримати актуальні конфігурації *одразу після запуску контейнерів виконайте оновлення конфігурацій (post actuator/refresh)* 

Eureka Server URL: http://localhost:8761  
Api Gateway URL: http://localhost:8080  
Service URL (instance 1): http://localhost:8082
Service URL (instance 2): http://localhost:8083
Config Server URL: http://localhost:8888
