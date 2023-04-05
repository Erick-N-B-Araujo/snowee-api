#!/bin/bash

echo "Logging in..."

docker login -u ericknbaraujo -p $PASS

echo "Versioning image..."

cd ./remote

echo $PWD

docker compose build

docker tag snoweegamecorp:latest ericknbaraujo/snoweegamecorp:latest

echo "Pushing version"

docker push ericknbaraujo/snoweegamecorp:latest
