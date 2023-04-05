#!/bin/bash

WORKSPACE=/var/lib/jenkins/workspace/Deploy-Backend

echo "Stopping app..."

echo $PWD

cd $WORKSPACE/remote/

echo $PWD

docker-compose down