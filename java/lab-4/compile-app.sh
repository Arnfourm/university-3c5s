#!/bin/bash

rm ./application.war
sudo rm -rf /var/lib/tomcat10/webapps/application*
mvn clean package
cp -r ./target/application.war /var/lib/tomcat10/webapps/
