#!/bin/bash

TARGET_DIR='build/'

cd ../kubernetes

if [ ! -d build ]; then
    mkdir build
fi

cp ../gateway/target/*.jar $TARGET_DIR
cp ../service-registry/target/*.jar $TARGET_DIR
cp ../auth/target/*.jar $TARGET_DIR
cp ../user/target/*.jar $TARGET_DIR
cp ../post/target/*.jar $TARGET_DIR

echo 'All COPY SUCCESS~~!'
