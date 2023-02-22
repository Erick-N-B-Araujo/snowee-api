#!/bin/bash

echo "Logging in..."

docker login -u ericknbaraujo -p $PASS

echo "Versioning image..."

docker tag backend_api:latest ericknbaraujo/snoweegamecorp:latest

echo "Pushing version"

docker push ericknbaraujo/snoweegamecorp:latest