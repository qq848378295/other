#!/bin/bash
echo  "please input you jdk path"
read javaPath
echo "export JAVA_HOME=${javaPath}" >> /etc/profile
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> /etc/profile
echo 'export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar'  >> /etc/profile
source /etc/profile #重新加载 profile
java -version #输出 jdk 版本信息