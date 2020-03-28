#!/bin/bash

if [ ! -d log ]; then
    mkdir log
fi

java -Dspring.porfiles.active=dev -jar ../gateway/target/*.jar 1>log/gateway.log 2>&1 &
echo "gateway run success"
java -Dspring.porfiles.active=dev -jar ../service-registry/target/*.jar 1>log/service-registry.log 2>&1 &
echo "service-registry run success"
java -Dspring.porfiles.active=dev -jar ../auth/target/*.jar 1>log/auth.log 2>&1 &
echo "auth run success"
java -Dspring.porfiles.active=dev -jar ../user/target/*.jar 1>log/user.log 2>&1 &
echo "user run success"
java -Dspring.porfiles.active=dev -jar ../post/target/*.jar 1>log/post.log 2>&1 &
echo "post run success"

cd ../face
npm start 1>../shell_script/log/face.log 2>1& &
echo "face run success"
