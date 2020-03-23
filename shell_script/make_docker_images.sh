#!/bin/bash
VERSION='v0.1.2'

cd ../gateway
docker build -t chohanjoo/mwohae-gateway:$VERSION .
echo '[Gateway] docker build SUCCESS~~!'

cd ../service-registry
docker build -t chohanjoo/mwohae-service-registry:$VERSION .
echo '[Service Registry] docker build SUCCESS~~!'

cd ../auth
docker build -t chohanjoo/mwohae-auth:$VERSION .
echo '[Auth] docker build SUCCESS~~!'

cd ../user
docker build -t chohanjoo/mwohae-user:$VERSION .
echo '[User] docker build SUCCESS~~!'

cd ../post
docker build -t chohanjoo/mwohae-post:$VERSION .
echo '[Post] docker build SUCCESS~~!'

cd ../face
docker build -t chohanjoo/mwohae-face:$VERSION .
echo '[Face] docker build SUCCESS~~!'


echo 'All docker images made!!'
