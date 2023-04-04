FROM maven:3.6.1-jdk-11-slim

RUN apt update && apt install net-tools