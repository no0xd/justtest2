#! /bin/sh

#export LD_LIBRARY_PATH=./

#JAVA_HOME="/usr/jdk64/jdk1.7.0_67"

#CLASSPATH="gat1400-client-1.0.0.jar"

#MAINCLASS="com.netposa.gat.GatClientStart"

#jvm_opts="-Xms3g -Xmx3g"

#nohup ${JAVA_HOME}/bin/java ${jvm_opts} -cp ${CLASSPATH} ${MAINCLASS} ${server_args} >/home/out.log 2>&1 &

#echo "Gat client 已启动.."


if [ -f "client.pid" ];then
	echo "Gat client already run"
	exit 1
else
	touch EXIST_PIDS.txt
	touch NEW_PIDS.txt
	
	export LD_LIBRARY_PATH=./

	JAVA_HOME="/usr/jdk64/jdk1.7.0_67"

	CLASSPATH="gat1400-client-1.0.0.jar"

	MAINCLASS="com.netposa.gat.GatClientStart"

	#jvm_opts="-Xms3g -Xmx3g"

	#启动进程之前查询已经存在的同名的进程 
	#=和$之间不能有空格，否则会报command not found
	EXIST_PIDS=$(ps ax | grep -i 'GatClientStart' | grep java | grep -v grep | awk '{print $1}')
	if [ ! -z "$EXIST_PIDS" ];then
		
		echo "$EXIST_PIDS" > EXIST_PIDS.txt
	fi
	nohup ${JAVA_HOME}/bin/java ${jvm_opts} -cp ${CLASSPATH} ${MAINCLASS} ${server_args} >/home/out.log 2>&1 &
        
        sleep 1

	#启动之后再次查询同名的进程
	NEW_PIDS=$(ps ax | grep -i 'GatClientStart' | grep java | grep -v grep | awk '{print $1}')
	
	echo "$NEW_PIDS" > NEW_PIDS.txt

	#求NEW_PIDS和EXIST_PIDS的差
	pid=$(grep -F -v -f EXIST_PIDS.txt NEW_PIDS.txt | sort | uniq)

	if [ ! -s "$client.pid" ];then
		touch client.pid
		echo "$pid" > client.pid
		rm -f EXIST_PIDS.txt NEW_PIDS.txt
	fi
	echo "Gat client 已启动.."
fi

