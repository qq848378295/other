package com.zhangxg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetAreaInfoApplicationTests {

	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	@Test
	public void contextLoads() throws Exception {
		
		
//		List list =jdbc.queryForList("select * from user");
//		for (Object object : list) {
//			System.out.println(JSON.toJSONString(object));
//		}
		
		long l1=System.currentTimeMillis();
		String html=FileUtil.readFile2String("D:/area.json");
		System.out.println("html:"+html.length());
		JSONArray array=JSONArray.parseArray(html);
		for (int i = 0; i < array.size(); i++) {
			JSONObject json=null;
			try {
				json=array.getJSONObject(i);
			} catch (Exception e) {
				System.out.println("xxxxxxxxxxxxxxx:"+array.get(i));
				e.printStackTrace();
				System.exit(0);
			}
			System.out.println(json);
			if(json==null) {
				continue;
			}
			String code=json.getString("code");
			Integer level=json.getInteger("level");
			String parent=json.getString("parent");;
			json.put("parent",parent);
			JSONObject _json=json;
			es.execute(new Runnable() {
				@Override
				public void run() {
					JSONObject json=_json;
					String name=json.getString("name");
					String type=json.getString("type");
					String str=json.getString("str");
					
					String sql="insert into t_area  (id,code,name,type,str,parent,level) "
							+ "values ('"+code+"','"+code+"','"+name+"','"+(type==null||"null".equals(type) ? "":type)+"','"+str+"','"+("".equals(parent)?"0":parent)+"','"+level+"')";
					try {
						jdbc.execute(sql);
					} catch (Exception e) {
						e.printStackTrace();
						System.err.println(sql);
//						System.exit(1);
					}
				}
			});
			System.out.println(json);
		}
		es.shutdown();
		while(! es.isTerminated()) {
		}
		long l2=System.currentTimeMillis();
		System.out.println("结束"+(l2-l1));
	}
	
	
	static ExecutorService es=Executors.newFixedThreadPool(31);
	 
 	

}
