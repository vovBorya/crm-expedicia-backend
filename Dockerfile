FROM maven:latest

RUN mkdir -p /app

ENV HOME_PATH /app
WORKDIR $HOME_PATH

COPY . /app/.

EXPOSE 8080 8888 22
