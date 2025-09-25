#!/bin/bash

rm ./application.war
sudo rm -rf /var/lib/tomcat10/webapps/application*
javac -cp ./libs/* -d ./webapps/java-application/WEB-INF/classes ./src/com/simple_java_application/*
cp -r ./libs/* ./webapps/java-application/WEB-INF/lib/
cd ./webapps/java-application/
jar cvf ../../application.war .
cd ../../
cp -r ./application.war /var/lib/tomcat10/webapps/
