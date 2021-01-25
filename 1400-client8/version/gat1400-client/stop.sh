#!/bin/sh
#PIDS=$(ps ax | grep -i 'GatClientStart' | grep java | grep -v grep | awk '{print $1}')

#if [ -z "$PIDS" ]; then
#  echo "No GatClientStart to stop"
#  exit 1
#else 
#  kill -s TERM $PIDS
#fi

#echo "Gat client服务已经停止...." 

#stop.sh

if [ ! -f "client.pid" ];then
	echo "No GatClientStart to stop"
	exit 1
else
	pid=$(cat client.pid)
	kill -9 $pid	
	echo "Gat client is stop..."
	rm -f client.pid
fi




