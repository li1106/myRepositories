package com.li;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageInfo;
import com.li.entity.Student;
import com.li.service.StudentService;

public class BootStart {
	static Logger log = Logger.getLogger(BootStart.class);

	public static void main(String[] args) throws IOException {
		log.trace("马上启动服务");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		StudentService studentService = context.getBean(StudentService.class);
		PageInfo<Student> list = studentService.list(1, 3);
		System.out.println("pageInfo is "+list);
		log.trace("启动ok......");
		System.in.read();
	}
}
