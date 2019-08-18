package com.example.demo;

import java.util.Date;

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
	
	public JSONObject findMessageById(Long id) {
		return mapper.findMessageById(id);
	}


	//先查看消息 是否處理歐國  如果處理過 直接發送消息 添加積分  發送消息
	public JSONObject addScore(JSONObject param) {
		Long messageId=param.getLong("id");
		JSONObject message=mapper.findMessageById(messageId);
		if(message==null) {
			Long userId=param.getLong("user_id");
			//添加積分
			JSONObject userScore=mapper.findUserScoreByUserId(userId);
			if(userScore==null) {
				mapper.insertUserScore(userId);
				userScore=mapper.findUserScoreByUserId(userId);
			}
			Integer score=param.getInteger("score");
			userScore.put("score", userScore.getInteger("score")+score);
			mapper.updateUserScore(userScore);
			
			//添加積分詳情
			JSONObject scoreDetail=new JSONObject();
			scoreDetail.put("user_id", userId);
			scoreDetail.put("score", score);
			scoreDetail.put("add_time",new Date());
			scoreDetail.put("remark", "來源:消息（+）:"+messageId);
			mapper.insertUserScoreDetail(scoreDetail);
		}
		//發送消息  確認事務 結束
		confirmTrans(messageId,"用戶積分增加完成");
		return param;
	}
	
	
	public void confirmTrans(Long messageId,String message) {
		JSONObject confirm =new JSONObject();
		confirm.put("id", messageId);
		confirm.put("message", message);
		redisTemplate.opsForList().leftPush(key, confirm.toString());
	}
	public static String key="pro1-trans-confirm";
	@Autowired
    private StringRedisTemplate redisTemplate;
}
