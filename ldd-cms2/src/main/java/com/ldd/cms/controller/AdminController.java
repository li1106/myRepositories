package com.ldd.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/***
 * 
 * @ClassName: AdminController 
 * @Description: 管理员的控制器
 * @author: charles
 * @date: 2019年7月19日 上午10:50:25
 */
@RequestMapping("admin")
@Controller
public class AdminController {
	
	
	@RequestMapping({"/","","index"})
	public String toIndex() {
		System.err.println("++++++++++++++++++++++");
		return "admin/index";
		
	}

}
