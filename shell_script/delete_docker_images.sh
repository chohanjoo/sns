#!/bin/bash
VERSION='v0.1.2'

echo `docker rmi chohanjoo/mwohae-gateway:$VERSION`
echo `docker rmi chohanjoo/mwohae-post:$VERSION`
echo `docker rmi chohanjoo/mwohae-user:$VERSION`
echo `docker rmi chohanjoo/mwohae-auth:$VERSION`
echo `docker rmi chohanjoo/mwohae-service-registry:$VERSION`
echo `docker rmi chohanjoo/mwohae-face:$VERSION`
echo `docker rmi chohanjoo/mwohae-generator:$VERSION`
