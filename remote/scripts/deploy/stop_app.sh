#!/bin/bash

WORKSPACE=/var/lib/jenkins/workspace/Deploy-Backend/local

echo "Stopping app..."

cd $WORKSPACE

docker-compose down