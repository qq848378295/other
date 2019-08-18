package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

@Repository
public interface CommonMapper {

	@Select("select * from  t_message_process where  id=#{id}")
	JSONObject findMessageById(@Param("id") Long id);

	int exists(String string);

	@Insert("insert into `user` (id,name,age) values (#{id},#{name},#{age})")
	void insertUser(JSONObject param);

	@Select("select * from  user_score where  user_id=#{userId} for update")
	JSONObject findUserScoreByUserId(@Param("userId") Long userId);

	@Insert("insert into user_score (user_id,score) values (#{userId},0)")
	void insertUserScore(@Param("userId") Long userId);

	@Insert("insert into user_score_detail (user_id,score,add_time,remark) values (#{user_id},#{score},#{add_time},#{remark})")
	void insertUserScoreDetail(JSONObject scoreDetail);

	@Update("update user_score set score=#{score} where user_id=#{user_id}")
	void updateUserScore(JSONObject userScore);

}
