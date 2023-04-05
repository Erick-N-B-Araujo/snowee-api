#!/bin/bash

WORKSPACE=/var/lib/jenkins/workspace/Deploy-Backend

echo "Stopping app..."

cd $WORKSPACE/remote/

docker-compose down