#!/bin/bash

WORKSPACE=/var/lib/jenkins/workspace/Deploy-Backend

echo "Logging in..."

docker login -u ericknbaraujo -p $PASS

echo "Downloading new image..."

docker pull ericknbaraujo/snoweegamecorp:latest

echo "Deploying app..."

cd $WORKSPACE/remote/

docker-compose up -d