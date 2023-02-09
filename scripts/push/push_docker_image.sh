#!/bin/bash

IMAGE="snoweegamecorp"

echo "Logging in..."

docker login -u ericknbaraujo -p $PASS

echo "Versioning image..."

docker tag $IMAGE:$BUILD_TAG ericknbaraujo/$IMAGE:$BUILD_TAG

echo "Pushing version"

docker push ericknbaraujo/$IMAGE:$BUILD_TAG

echo "Tagging image..."

docker tag $IMAGE:$BUILD_TAG ericknbaraujo/$IMAGE:latest

echo "Pushing image"

docker push ericknbaraujo/$IMAGE:latest