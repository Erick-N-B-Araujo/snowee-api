#!/bin/bash

WORKSPACE=/var/lib/jenkins/workspace/Backend/local

echo "Stopping app..."

cd $WORKSPACE

docker-compose down