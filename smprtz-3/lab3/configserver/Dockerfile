# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM openjdk:11-jre-slim
RUN apt-get update
RUN apt-get -y install git
RUN git config --global user.email "you@example.com"
RUN git config --global user.name "Your Name"
COPY config config
COPY --from=build /home/app/target/configserver-0.0.1-SNAPSHOT.jar /usr/local/lib/configserver.jar
EXPOSE 8888
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh .
RUN chmod 777 wait-for-it.sh
CMD ./wait-for-it.sh eurekaserver:8761 --timeout=400 --strict -- java -jar /usr/local/lib/configserver.jar