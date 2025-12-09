#!/bin/bash

# Удаление старо версии war с локальной директории
rm ./application-lab-7.war

# Удаление старой версии c tomcat10 сервера
sudo rm -rf /var/lib/tomcat10/webapps/application-lab-7*

# Сборка приложения
mvn clean package

# Перенос всего проекта с локальной директории targer на tomcat10 сервер
cp -r ./target/application-lab-7.war /var/lib/tomcat10/webapps/
