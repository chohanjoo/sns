#!/bin/bash
ID=admin
PW=admin
JWT=`curl -X POST "http://localhost:8000/api/auth/signin" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"$ID\", \"username\": \"$PW\"}" | jq '.data' -r`

while :
do
    echo `curl -X GET "http://localhost:8000/api/generator/api/post" -H "accept: */*" -H "X-AUTH-TOKEN:$JWT"`
    echo `curl -X GET "http://localhost:8000/api/generator/api/friend" -H "accept: */*" -H "X-AUTH-TOKEN:$JWT"`
    echo `curl -X GET "http://localhost:8000/api/generator/api/user" -H "accept: */*" -H "X-AUTH-TOKEN:$JWT"`
    
    sleep 3
    
    curl -X POST "http://localhost:8000/api/post" -H "accept: */*" -H "X-AUTH-TOKEN:$JWT" -H "Content-Type: application/json" -d "{ \"user_id\": \"$ID\"}"
    curl -X GET "http://localhost:8000/api/user/friend?user_id=$ID" -H "accept: */*" -H "X-AUTH-TOKEN:$JWT"
done
