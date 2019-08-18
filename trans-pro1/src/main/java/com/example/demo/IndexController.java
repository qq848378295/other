package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
public class IndexController {
	
	@Autowired
	private UserService  service;
	@RequestMapping("/register")
	public Object register(@RequestBody JSONObject param) {
		return service.register(param);
	}
	
	@Autowired
    private StringRedisTemplate redisTemplate;

	private String key="user-register";
	@RequestMapping("/send")
	public Object send() {
		redisTemplate.opsForList().leftPush(key, new Date().toString()+"中文");
		return "success";
	}
	
}
