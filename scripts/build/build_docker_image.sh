#!/bin/bash

WORKSPACE=/home/jenkins/snoweegamecorp/jenkins-data/jenkins_home/workspace/snowee-api

# Copy the new jar to the build location
cp -f $WORKSPACE/java-app/target/*.jar $WORKSPACE/scripts/build/

echo "Bbuilding docker image..."

# Building docker image
cd $WORKSPACE/scripts/build/ && docker-compose -f docker-compose_build.yml build --no-cache 