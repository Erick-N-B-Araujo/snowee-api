#!/bin/bash

echo "Cleaning all the trash..."

rm -rf java-app/core.*
rm -rf java-app/hs_err_pid*
rm -rf java-app/target

echo "Finished!"