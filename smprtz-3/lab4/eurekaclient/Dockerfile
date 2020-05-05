FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/eurekaclient-0.0.1-SNAPSHOT.jar /usr/local/lib/eurekaclient.jar
EXPOSE 8081
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh .
RUN chmod 777 wait-for-it.sh
CMD ./wait-for-it.sh spring-cloud-eureka-feign-client:8080 --timeout=400 --strict -- java -jar /usr/local/lib/eurekaclient.jar