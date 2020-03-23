#!/bin/bash

cd ../gateway
./mvnw clean
./mvnw install
echo '[Gateway] SUCCESS'

cd ../service-registry
./mvnw clean
./mvnw install
echo '[Service-registry] SUCCESS'

cd ../auth
./mvnw clean
./mvnw install
echo '[Auth] SUCCESS'

cd ../user
./mvnw clean
./mvnw install
echo '[User] SUCCESS'

cd ../post
./mvnw clean
./mvnw install
echo '[Post] SUCCESS'
