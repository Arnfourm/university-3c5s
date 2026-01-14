#!/bin/bash

rm ./application-lab-6.war
sudo rm -rf /var/lib/tomcat10/webapps/application-lab-6*
mvn clean package
cp -r ./target/application-lab-6.war /var/lib/tomcat10/webapps/
