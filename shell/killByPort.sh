#!/bin/bash
# 使用  ./killByPort.sh 80 
pid0=$(netstat -nlp | grep :$1 | awk '{print $7}' | awk -F"/" '{ print $1 }');
echo "pid0 $pid0";
kill -s 9 $pid0 ;