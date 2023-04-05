#!/bin/bash

echo "Maven command..."

WORKSPACE=/home/jenkins/snoweegamecorp/jenkins_home/workspace/Backend

docker run --rm -v $WORKSPACE/local/java-app:/app  -v /root/.m2/:/root/.m2/ -w /app maven:3-alpine "$@"