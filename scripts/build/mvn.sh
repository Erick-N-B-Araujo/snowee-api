#!/bin/bash

echo "Maven command..."

WORKSPACE=/home/jenkins/snoweegamecorp/jenkins-data/jenkins_home/workspace/snowee-api

docker run --rm -v $WORKSPACE/java-app:/app  -v /root/.m2/:/root/.m2/ -w /app maven:3-alpine "$@"