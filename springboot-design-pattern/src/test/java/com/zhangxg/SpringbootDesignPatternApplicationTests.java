package com.zhangxg;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.zhangxg.factory.simplefactory.mydemo.Phone;
import com.zhangxg.util.SpringUtil;
public class SpringbootDesignPatternApplicationTests {
	@Autowired
	@Qualifier("Oppo")
	private Phone phone;
	@Test
	public void contextLoads() {
		factoryTest();
	}
	public void factoryTest() {
		phone.start();
		Phone s2=SpringUtil.getBean("Mi",Phone.class);
		s2.start();
	}

}
