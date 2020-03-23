#!/bin/bash

cd ../kubernetes/deployment

echo `kubectl apply -f gateway_deployment.yaml`
echo `kubectl apply -f service-registry_deployment.yaml`
echo `kubectl apply -f auth_deployment.yaml`
echo `kubectl apply -f user_deployment.yaml`
echo `kubectl apply -f post_deployment.yaml`
echo `kubectl apply -f face_deployment.yaml`

echo '[SUCCESS]'
