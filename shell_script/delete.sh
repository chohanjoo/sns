#!/bin/bash

cd ../kubernetes/deployment

echo `kubectl delete -f gateway_deployment.yaml`
echo `kubectl delete -f service-registry_deployment.yaml`
echo `kubectl delete -f auth_deployment.yaml`
echo `kubectl delete -f user_deployment.yaml`
echo `kubectl delete -f post_deployment.yaml`
echo `kubectl delete -f face_deployment.yaml`

echo '[SUCCESS]'
