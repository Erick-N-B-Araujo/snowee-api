#!/bin/bash

WORKSPACE=/var/lib/jenkins/workspace/Deploy-Backend/local

echo "Deploying app..."

cd $WORKSPACE

docker-compose up -d