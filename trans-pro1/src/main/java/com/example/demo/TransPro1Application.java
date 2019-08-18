package com.example.demo;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alibaba.fastjson.JSONObject;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.mapper"})
public class TransPro1Application {

	public static void main(String[] args) {
		SpringApplication.run(TransPro1Application.class, args);
	}
	
	
	@Autowired
	private UserService service;

	//消息 重試機制 ： 保證最終一致性
	//搞個定時任務  間隔一定時間   將消息表中  dead 為0 的數據 取出來 全部塞到隊列中去 
	@Bean
	public void a() {
		new Thread(() -> {
			while(true) {//簡單點  直接用sleep  //兩分鐘重發一次消息 
				try {
					List<JSONObject> list=service.selectAllFailMessage();
					for (JSONObject json : list) {
						service.reSend(json);
					}
					Thread.sleep(1000*60*2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	@Bean
	public TransConfirmConsumer tranConfirm() {
		TransConfirmConsumer bean=new TransConfirmConsumer();
		bean.start();
		return bean;
	}
}
