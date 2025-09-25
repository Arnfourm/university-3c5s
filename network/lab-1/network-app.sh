#!/bin/bash

echo "    1 - show all network interfaces
    2 - show all open ports
    3 - show route table 
    4 - ping service/host 
    5 - show trace route to service/host 
    6 - get mac address 
    7 - get NetBIOS stat of service/host
    8 - check dns resolve to service/host"

while true
do  
  read -p "Choose number on your keyboard (from 1 to 8): " action_number 
  
  if [[ "$action_number" == "1" ]]; then
    ip a
  elif [[ "$action_number" == "2" ]]; then
    netstat -ntulp
  elif [[ "$action_number" == "3" ]]; then
    route
  elif [[ "$action_number" == "4" ]]; then
    read -p "write URL or ip address of service/host: " host_action
    ping $host_action
  elif [[ "$action_number" == "5" ]]; then
    read -p "write URL or ip address of service/host: " host_action
    traceroute $host_action
  elif [[ "$action_number" == "6" ]]; then
    ip a | grep ether
  elif [[ "$action_number" == "7" ]]; then
    read -p "write URL or ip address of service/host: " host_action
    nmblookup -A $host_action
  elif [[ "$action_number" == "8" ]]; then
    read -p "write URL or ip address of service/host: " host_action
    nslookup $host_action
  else
    echo "You chose wrong char"
  fi
done
