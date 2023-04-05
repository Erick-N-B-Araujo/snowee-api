#!/bin/bash

echo "Logging in..."

docker login -u ericknbaraujo -p $PASS

echo "Downloading new image..."

docker pull ericknbaraujo/snoweegamecorp:latest

echo "Deploying app..."

docker-compose up -d