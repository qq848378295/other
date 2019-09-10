package cn.zhangxg.designpattern;

import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) throws Exception {
		Thread.sleep(5000);
		String s="Hello World!";
		List<String> list=new ArrayList<String>();
		for(int i=0;i<3000;i++) {
			String s1=new String();
			System.out.println(s1);
			list.add(s1.intern());
			Thread.sleep(200);
		}
		
	}
}
