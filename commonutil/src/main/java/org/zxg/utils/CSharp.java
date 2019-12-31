package org.zxg.utils;

import com.alibaba.fastjson.JSONObject;

public class CSharp {
    public static void main(String[] args) {
        JSONObject json=new JSONObject();
        String url="http://www.baidu.com";
        json.put("url",url);
        String cmd="D:\\sjht_workspace\\git-other\\vs\\ConsoleApp1\\ConsoleApp1\\bin\\Debug\\ConsoleApp1.exe "
//                +json.toString();
                +url;
        String result=MyRunTimeUtil.execCmdWithResult(cmd);
        System.out.println(result);
    }
}
