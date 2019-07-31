package com.zhangxg;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
	static String PATH="I:/sjhtwork/community/get-area-info/area-info/";
	static long l1=System.currentTimeMillis();
	public static void main(String[] args) throws Exception {
		
		getAllYearData();//把所有的全部保存到本地
		//指定某一个去抓取
		//获取所有省数据
		
		JSONObject json=new JSONObject();
		String url="http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/index.html";
		json.put("year", "2018年");
		json.put("url", url);
		getProvinces(json);
		Thread.sleep(10000000);
		
	}
	
	static ExecutorService execs=Executors.newFixedThreadPool(3);
	//获取所有省份
	private static void getProvinces(JSONObject _json) throws IOException {
		
		System.out.println("getAllCitys 省 请求:"+_json.toString());
		String url=_json.getString("url");
		Document doc=null;
		Elements es=null;
		while(doc==null) {
			try {
				doc=Jsoup.connect(url).ignoreContentType(true).get();
			} catch (Exception e) {
				System.out.println("47"+_json);
				try {
					Thread.sleep(3000);
				} catch (Exception e1) {
				}
			}
			if(doc!=null) {
				es=doc.select(".provincetr a");
				if(es.size()==0) {
					System.out.println("56被限制访问");
					doc=null;
					try {
						Thread.sleep(3000);
					} catch (Exception e1) {
					}
				}
			}
		}
//		Elements es=doc.select(".provincetr a");
		for (Element e : es) {
//			System.exit(0);
			execs.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(e.absUrl("href")+"\t"+e);
					JSONObject json=new JSONObject();
					json.put("url", e.absUrl("href"));
					json.put("code", e.attr("href").replace(".html", "0000000000"));
					json.put("name", e.text());
					json.put("level", "1");
					json.put("parent", "0");
					if(! new File(PATH+"/"+_json.getString("year")+"/"+json.getString("code")+".json").exists()) {
						getAllCitys(json);
						FileUtil.writeString(PATH+"/"+_json.getString("year")+"/"+json.getString("code")+".json", json.toString());
					}
				}
			});
		}
		
//		execs.shutdown();
		
		/*
		 * try { while(!execs.isShutdown()) { Thread.sleep(1); } long
		 * l2=System.currentTimeMillis(); System.err.println("耗时："+(l2-l1)); while(true)
		 * { Thread.sleep(10000); } } catch (Exception e) { }
		 */
		
		/*
		 * while(true) { Thread.sleep(10000); }
		 */
	}

	//市
	private static void getAllCitys(JSONObject json) {
		String _url=json.getString("url"); 
		System.out.println("getAllCitys 市 请求:"+json.toString());
		Document doc =null;
		Elements es=null;
		while(doc==null) {
			try {
				doc = Jsoup.connect(_url).timeout(5000).ignoreContentType(true).get();
			} catch (Exception e) {
				System.out.println("85"+json);
				try {
					Thread.sleep(3000);
				} catch (Exception e1) {
				}
			}
			 if(doc!=null) {
				 es=doc.select(".citytr");
					if(es.size()==0) {
						doc=null;
					}
			 }
		}
		JSONArray array=new JSONArray();
		for (Element element : es) {
			Elements els=element.select("a");
			String url=els.get(0).absUrl("href");
			String code=els.get(0).text();
			String name=els.get(1).text();
//				System.out.println(url +"\tcode:"+code+"\tname:"+name);
			
			JSONObject child=new JSONObject();
			child.put("url",url);
			child.put("code",code);
			child.put("name",name);
			child.put("level", "2");
			child.put("parent",json.getString("code"));
			getCountys(child);
			array.add(child);
		}
		json.put("data", array);
	
	}

	private static void getCountys(JSONObject json) {
		String _url=json.getString("url"); 
		System.out.println("getCountys 县 请求:"+json.toString());
		Document doc = null;
		Elements es=null;
		while(doc==null) {
			try{
			 doc = Jsoup.connect(_url).timeout(5000).ignoreContentType(true).get();
			}catch (Exception e) {
				System.out.println("116"+json);
				try {
					Thread.sleep(3000);
				} catch (Exception e1) {
				}
			}
			if(doc!=null) {
				es=doc.select(".countytr");
				if(es.size()==0) {
					doc=null;
				}
			}
			
		}
		
		JSONArray array=new JSONArray();
		json.put("data", array);
		for (Element element : es) {
			Elements els=element.select("a");
			try {
				JSONObject child=new JSONObject();
				if(els.size()==0) {//没有下一级了
					els=element.select("td");
				}else {
					String url=els.get(0).absUrl("href");
					child.put("url",url);
					getTowns(child);
				}
				String code=els.get(0).text();
				String name=els.get(1).text();
				child.put("code",code);
				child.put("name",name);
				child.put("level", "3");
				child.put("parent",json.getString("code"));
				array.add(child);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
	}

	//镇
	private static void getTowns(JSONObject json) {
		String _url=json.getString("url"); 
		System.out.println("getTowns 镇 请求:"+json.toString());
		Document doc=null;
		Elements es=null;
		while(doc==null) {
			try {
				doc=Jsoup.connect(_url).ignoreContentType(true).get();
			} catch (Exception e) {
				System.out.println("156"+json);
				try {
					Thread.sleep(3000);
				} catch (Exception e1) {
				}
			}
			
			 if(doc!=null) {
				 es=doc.select(".towntr");
				 if(es.size()==0) {
					doc=null;
				}
			 }
		}
		 
		JSONArray array=new JSONArray();
		for (Element element : es) {
			Elements els=element.select("a");
			String url=els.get(0).absUrl("href");
			String code=els.get(0).text();
			String name=els.get(1).text();
			JSONObject child=new JSONObject();
			child.put("url",url);
			child.put("code",code);
			child.put("name",name);
			child.put("level", "4");
			child.put("parent",json.getString("code"));
			getVillagetrs(child);
			array.add(child);
		}
		json.put("data", array);
	}
	
	//村
	private static void getVillagetrs(JSONObject json) {
		String _url=json.getString("url"); 
		System.out.println("getVillagetrs 村 请求:"+json.toString());
		Document doc=null;
		Elements es=null;
		while(doc==null) {
			try {
				doc = Jsoup.connect(_url).timeout(5000).ignoreContentType(true).get();
			} catch (Exception e) {
				System.out.println("188:"+json);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			if(doc!=null) {
				es=doc.select(".villagetr");
				if(es.size()==0) {
					doc=null;
				}
			}
		}
		
		JSONArray array=new JSONArray();
		for (Element element : es) {
			Elements els=element.select("td");
//				String url=els.get(0).absUrl("href");
			String code=els.get(0).text();
			String name=els.get(2).text();
			JSONObject child=new JSONObject();
//				child.put("url",url);
			child.put("code",code);
			child.put("name",name);
			child.put("level", "5");
			child.put("parent",json.getString("code"));
			array.add(child);
		}
		json.put("data", array);
	}

	private static JSONArray getAllYearData() throws Exception {
		String filePath=PATH+"all.json";
		if(new File(filePath).exists()) {
			String jsonStr=FileUtil.readFile2String(filePath);
			JSONObject x=JSON.parseObject(jsonStr);
			return x.getJSONArray("data");
		}
		try {
			String url="http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/";
			Document doc= Jsoup.connect(url).ignoreContentType(true).get();
			Elements es= doc.select(".center_list_contlist li a");
			JSONObject  info=new JSONObject();
			JSONArray array=new JSONArray();
			info.put("url", url);
			info.put("data", array);
			for (Element e : es) {
				String path=PATH+e.select("font").get(0).text();
				if(!new File(path).exists()) {
					new File(path).mkdirs();
				}
				JSONObject json=new JSONObject();
				json.put("path", path);
				json.put("year",e.select("font").get(0).text());
				json.put("date", e.select("font").get(1).text());
				json.put("url", e.absUrl("href"));
				array.add(json);
			}
			FileUtil.writeString(filePath,info.toString());
			return array;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return getAllYearData();
		}
		
	}
	
	
}
