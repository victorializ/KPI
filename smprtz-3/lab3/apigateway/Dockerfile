# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/apigateway-0.0.1-SNAPSHOT.jar /usr/local/lib/apigateway.jar
EXPOSE 8080
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh .
RUN chmod 777 wait-for-it.sh
CMD ./wait-for-it.sh configserver:8888 --timeout=400 --strict -- java -jar /usr/local/lib/apigateway.jar