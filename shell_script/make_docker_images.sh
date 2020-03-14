#!/bin/bash

cd ../gateway
docker build -t chohanjoo/mwohae-gateway:v0.1 .
echo '[Gateway] docker build SUCCESS~~!'

cd ../service-registry
docker build -t chohanjoo/mwohae-servie-registry:v0.1 .
echo '[Service Registry] docker build SUCCESS~~!'

cd ../auth
docker build -t chohanjoo/mwohae-auth:v0.1 .
echo '[Auth] docker build SUCCESS~~!'

cd ../user
docker build -t chohanjoo/mwohae-user:v0.1 .
echo '[User] docker build SUCCESS~~!'

cd ../post
docker build -t chohanjoo/mwohae-post:v0.1 .
echo '[Post] docker build SUCCESS~~!'

echo 'All docker images made!!'
