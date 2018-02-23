#!/bin/bash
set -e

mvn clean install
mvn pmd:check
mvn findbugs:check

mvn clean package assembly:single

which docker

if [ $? -eq 0 ]
then
    docker build -t rpn .
fi
