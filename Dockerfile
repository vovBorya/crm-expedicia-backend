FROM maven:latest

RUN mkdir -p /app

ENV HOME_PATH /app
WORKDIR $HOME_PATH

COPY . /app/.

RUN mvn clean package

EXPOSE 8080 22
