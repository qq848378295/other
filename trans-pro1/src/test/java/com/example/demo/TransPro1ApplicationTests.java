package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransPro1ApplicationTests {
	@Autowired
	private UserService  service;
	
	@Test
	public void contextLoads() {
		JSONObject json = service.findMessageById(1L);
		System.out.println(json);
	}

}
