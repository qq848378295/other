package com.zhangxg;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 *  1因为会被封,所以需要借助wget 先把文件全部下载到本地
 *  2分析并解析 得到数据
 * @author zhangxg
 */
public class Main {
	//update t_area set code=code/1000 where code like '%%' and `level`=3 and code%1000=0  这是导入数据的处理
// 下载7代表深度    wget -np -m -l7 http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/
	static String PATH="D:/2018";
	public static void main(String[] args) throws Exception {
		long l1=System.currentTimeMillis();
		File path=new File(PATH);
		show(path,1);
		es.shutdown();
		while(! es.isTerminated()) {
		}
		long l2=System.currentTimeMillis();
		System.out.println("结束"+(l2-l1));
		
		FileUtil.writeString("d:/area.json", array.toString());
		long l3=System.currentTimeMillis();
		System.out.println("结1束"+(l3-l2));
	}
//	static ExecutorService es=Executors.newFixedThreadPool(31);
	
	static ExecutorService es=Executors.newFixedThreadPool(31);
	static Set<String> set=new HashSet<String>();
	
	private static void show(File file, int i) {
		System.out.println(i+"\t"+file.getAbsolutePath()+"\t"+Thread.currentThread().getName());
		if(i==1) {
			File[] listFiles=file.listFiles();
			for (File e: listFiles) {
				es.execute(new Runnable() {
					@Override
					public void run() {
						if(e.isDirectory()) {
							show(e, 3);
						}else {
							read(e,2);
						}
					}
				});
				
			}
		}else {
			File[] listFiles=file.listFiles();
			for (File e: listFiles) {
				int j=i+1;
				if(e.isDirectory()) {
					show(e, j);
				}else {
					read(e,j);
				}
			}
			
		}
	}
	static JSONArray array=new JSONArray();
	private static void read(File file, int level) {
		try {
			String html=FileUtil.readFile2String(file.getAbsolutePath(),"gbk");
			Document doc=Jsoup.parse(html);
			Elements es=doc.select("table table table table tr");
			if(es.size()==0) {
				System.err.println(file.getAbsolutePath()+"\t\r\n"+html);
				System.exit(1);
			}
			
			for (int i=0;i<es.size();i++) {
				JSONObject json=new JSONObject();
				Element e=es.get(i);
				Elements els=e.select("td");
				 
				String str=null;
				String code=null;
				String type=null;
				String name=null;
				if(els.size()==2) {
					str=e.attr("class");
					code=els.get(0).attr("href").replace(".html", "");
					if("".equals(code) || code==null){
						code=els.get(0).text();
					}
					if(code.contains("统计用区划代码")) {
						continue;
					}
					name=els.get(1).text();
//					System.out.println( str+"\t"+code+"\t"+name);
				}else if(els.size()==3) {
					str=e.attr("class");
					code=els.get(0).text();
					if(code.contains("统计用区划代码")) {
						continue;
					}
					type=els.get(1).text();
					name=els.get(2).text();
//					System.out.println(str+"\t"+code+"\ttype:"+type+"\t"+name);
				}else {
					if("index.html".equals(file.getName())) {
						System.out.println(file.getAbsolutePath()+"\t\r\n"+html);

//						String html=FileUtil.readFile2String("C:\\Users\\29106\\Downloads\\2018\\index.html","gbk");
//						Document doc=Jsoup.parse(html);
						//System.out.println(html);
						 els=doc.select(".provincetr a");
						for (Element element : els) {
							json=new JSONObject();
							json.put("str", "provincetr");
							json.put("code", element.attr("href").replace(".html", "0000000000"));
							json.put("name", element.text());
							json.put("level", 1);
							json.put("type", "");
							json.put("parent", "0");
							String key=json.getString("code");
//							if(!set.contains(key)) {
								array.add(json);
								set.add(key);
//							}
						}
						continue;
					}
					System.err.println(els.size());
					System.err.println(file.getAbsolutePath()+"\t\r\n"+e.html());
					System.exit(1);
					
				}
				json.put("str", str);
				json.put("code", code);
				json.put("name", name);
				json.put("level", level);
				json.put("type", type);
				json.put("parent", file.getName().replace(".html", ""));
				System.out.println(json);
				
				String key=json.getString("code");
//				if(!set.contains(key)) {
					array.add(json);
					set.add(key);
//				}
//				array.add(json);
//				set.add(json);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			
			
		}
	}		
	
}
