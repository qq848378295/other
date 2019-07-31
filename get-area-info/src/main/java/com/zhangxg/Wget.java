package com.zhangxg;

import java.io.IOException;

public class Wget {
	public static void main(String[] args) {
//		String s="wget -c http://beijing.tianqi.com -O  d:/image/beijing.txt";
//	   	execute(s);
//	   	download("http://beijing.tianqi.com", "d://image//beijing.txt");
		String s="wget -c http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html -O  I:/sjhtwork/community/get-area-info/area-info/2018年/index.html";
		execute(s);
//		download("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html", "I:\\sjhtwork\\community\\get-area-info\\area-info\\2018年\\index.html");
	}
	public static boolean download(String filePath,String outPath) {
		String cmd =    " wget -c \""+filePath+"\""+" -O "+outPath;
		//System.out.println(cmd);
		try {//执行命令
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return true;
	}
	
	public static void execute(String cmd){
		try {//执行命令
			 Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
