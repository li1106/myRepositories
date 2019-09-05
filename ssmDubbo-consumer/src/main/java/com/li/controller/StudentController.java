package com.li.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.li.domain.Student;
import com.li.service.StudentService;

@Controller
@RequestMapping("stu")
public class StudentController {
	@Reference
	StudentService stuService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		PageInfo<Student> pageInfo = stuService.list(1,10);
		request.setAttribute("pageInfo", pageInfo);
		return "list";
	}
}
