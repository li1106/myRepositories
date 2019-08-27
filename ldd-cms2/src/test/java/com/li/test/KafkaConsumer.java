package com.li.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KafkaConsumer {
	public static void main(String[] args) {
		//创建上下文对象
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-beans.xml","classpath:spring-consumer.xml");
	}
}
