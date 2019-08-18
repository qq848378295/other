package com.example.demo;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.CommonMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
	@Autowired
	private CommonMapper  mapper;
	@Autowired
    private StringRedisTemplate redisTemplate;

	private String key="user-register";
	
	public JSONObject findMessageById(Long id) {
		return mapper.findMessageById(id);
	}

	public JSONObject register(JSONObject param) {
		JSONObject result=new JSONObject();
		result.put("status", 0);
		//先判断  是否存在
		if(mapper.exists(param.getString("name")) >0) {
			result.put("status", 1);
			result.put("message", "用户已存在");
			return result;
		}
		Long userId=new Date().getTime()+(random.nextInt(8999)+1000);
		param.put("id", userId);
		
		//添加用户 
		mapper.insertUser(param);
		//添加消息
		JSONObject message=new JSONObject();
		Long id=new Date().getTime()+(random.nextInt(8999)+1000);
		message.put("id",id);
		message.put("add_time", new Date());
		message.put("edit_time", new Date());
		message.put("send_count",1);
		message.put("dead",0);
		message.put("type", key);
		
		JSONObject body=new JSONObject();
		
		body.put("id",id);
		body.put("user_id",userId);
		body.put("score",param.getInteger("age")*10);
		message.put("body", body.toString());
		//插入消息 
		mapper.insertMessage(message);
		//发送消息
		sendMessage(body.toString());
		return message;
	}

	private void sendMessage(String body) {
		redisTemplate.opsForList().leftPush(key, body);
	}
	Random random=new Random();

	public void deadMessage(JSONObject json) {
		mapper.deadMessage(json.getLong("id"));
	}

	public List<JSONObject> selectAllFailMessage() {
		return mapper.selectAllFailMessage();
	}

	//重新發送
	public void reSend(JSONObject json) {
		json.put("send_count",json.getInteger("send_count")+1);
		json.put("edit_time",new Date());
		mapper.reSendMessage(json);//修改重發次數
		sendMessage(json.getString("body"));//發送消息
	}
}
