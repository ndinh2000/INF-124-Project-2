#!/bin/sh
mvn clean package && docker build -t com.mycompany/test .
docker rm -f test || true && docker run -d -p 9080:9080 -p 9443:9443 --name test com.mycompany/test