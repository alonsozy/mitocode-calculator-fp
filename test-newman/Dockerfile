FROM maven:3.6.3-jdk-11-slim AS builder

ENV URL_SERVER=http://192.168.99.100:8080

COPY calculator-collection.json .
#run test services with newman
RUN apt-get update && apt-get upgrade -y && apt-get install -y nodejs npm 
RUN npm install -g newman
ENTRYPOINT newman run calculator-collection.json  --global-var url_server=${URL_SERVER}


