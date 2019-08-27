package com.ldd.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ldd.cms.domain.Article;
import com.ldd.cms.domain.Collects;
import com.ldd.cms.domain.User;
import com.ldd.cms.service.CollectsServcie;
import com.ldd.cms.utils.CMSConstant;

@Controller
public class CollectsController {
	@Resource
	private CollectsServcie collectsServcie;
	
	@RequestMapping("/collectsController")
	@ResponseBody
	public boolean collectsController(Collects entity){
		return collectsServcie.insertObject(entity);
	}
	
	@RequestMapping("/disCollect")
	@ResponseBody
	public boolean disCollect(Collects entity){
		return collectsServcie.deleteObject(entity);
	}
	
	@RequestMapping("/toCollectList")
	public String toCollectList(HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(CMSConstant.LOGIN_GENERAL);
		Integer id = user.getId();
		List<Collects> collects = collectsServcie.findCollectsById(id);
		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < collects.size(); i++) {
			ids.add(collects.get(i).getUser().getId());
		}
		System.out.println(ids+"===");
		List<Article> articles = collectsServcie.findCollectsByUserId(ids);
		model.addAttribute("articles", articles);
		for (Article article : articles) {
			System.err.println(article);
		}
		return "my/collects/articles";
	}
}
