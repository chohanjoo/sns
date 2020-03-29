#!/bin/bash

APP_NAME=$1
PORT=$2
VERSION=$3

cd ../kubernetes

kubectl create deployment $APP_NAME --image=chohanjoo/mwohae-$APP_NAME':'$VERSION --dry-run -o=yaml > $APP_NAME'_deployment.yaml'

echo --- >> $APP_NAME'_deployment.yaml'

kubectl create service clusterip $APP_NAME --tcp=$PORT:$PORT --dry-run -o=yaml >> $APP_NAME'_deployment.yaml'

echo `kubectl apply -f $APP_NAME'_deployment.yaml'`
