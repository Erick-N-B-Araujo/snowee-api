#!/bin/bash

echo "Deleting resources..."

WORKSPACE=/var/jenkins_home/workspace/snowee-api

# Delete cached app resources
rm -rf $WORKSPACE/jenkins-data/maven/java-app/*

echo "Copying resources..."

# Copy the new jar to the build location
cp -r /home/jenkins/snowee-api/* $WORKSPACE/jenkins-data/maven/java-app