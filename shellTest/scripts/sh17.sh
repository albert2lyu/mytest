#!/bin/bash
# Program
#       Use ping command to check the network's PC state.
# History
# 2009/02/18    VBird   first release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH
network="192.168.1"              # 先定義一個網域的前面部分！
for sitenu in $(seq 1 100)       # seq 為 sequence(連續) 的縮寫之意
do
	# 底下的程式在取得 ping 的回傳值是正確的還是失敗的！
        ping -c 1 -w 1 ${network}.${sitenu} &> /dev/null && result=0 || result=1
        if [ "$result" == 0 ]; then
                echo "Server ${network}.${sitenu} is UP."
        else
                echo "Server ${network}.${sitenu} is DOWN."
        fi
done
