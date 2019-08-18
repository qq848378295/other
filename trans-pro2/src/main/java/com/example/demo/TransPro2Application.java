package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.mapper"})
public class TransPro2Application {

	public static void main(String[] args) {
		SpringApplication.run(TransPro2Application.class, args);
	}

	@Bean
	public Consumer consumer() {
		Consumer consumer= new Consumer();
		consumer.start();
		return consumer;
	}
}
