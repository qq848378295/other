package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

@Repository
public interface CommonMapper {

	@Select("select * from  t_message where  id=#{id}")
	JSONObject findMessageById(@Param("id") Long id);

	@Select("select count(0) from  `user` where name=#{name}")
	int exists( String string);

	@Insert("insert into `user` (id,name,age) values (#{id},#{name},#{age})")
	void insertUser(JSONObject param);

	@Insert("insert into `t_message` (id,add_time,edit_time,send_count,dead,type,body) values (#{id},#{add_time},#{edit_time},#{send_count},#{dead},#{type},#{body,jdbcType=VARCHAR})")
	void insertMessage(JSONObject message);
	
	@Update("update t_message set dead=1 where id=#{id} ")
	void deadMessage(@Param("id") Long id);
	
	@Select("select * from  t_message where dead=0")
	List<JSONObject> selectAllFailMessage();

	@Update("update t_message set send_count=#{send_count},edit_time=#{edit_time} where id=#{id}")
	void reSendMessage(JSONObject json);

}
