package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSON;

@Configurable
public class TransConfirmConsumer extends Thread {
	@Autowired
    private StringRedisTemplate redisTemplate;

	@Autowired
	private UserService service;
	@Override
	public void run() {
		System.out.println("consumer  start .....................");
		while (true){
			//阻塞式brpop，List中无数据时阻塞，参数0表示一直阻塞下去，直到List出现数据
//			List<String> listingList = redis.brpop(0, "listingList");
//			log.info("线程取数据：{}", listingList.get(1));
			if(redisTemplate==null) {
			}
			if(redisTemplate.opsForList()==null) {
				continue;
			}
			String json=redisTemplate.opsForList().rightPop(key);
			if(json!=null) {
				System.out.println("確認事務："+json);
				service.deadMessage(JSON.parseObject(json));
			}
		}
	}
	String key="pro1-trans-confirm";
}
