/**
 * 
 */
package com.li.cms.web.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.li.cms.domain.Article;
import com.li.cms.domain.User;
import com.li.cms.service.ArticleService;
import com.li.cms.service.UserService;
import com.li.cms.utils.FileUploadUtil;
import com.li.cms.utils.PageHelpUtil;
import com.li.cms.web.Constant;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2018年1月10日 下午2:40:38
 */
@Controller
@RequestMapping("/my")
public class UserController {

	@Autowired
	ArticleService articleService;
	@Autowired
	UserService userService ;
	
	@RequestMapping({"/", "/index", "/home"})
	public String home(){
		return "user-space/home";
	}
	
	@RequestMapping({"/profile"})
	public String profile(){
		return "user-space/profile";
	}
	
	@RequestMapping("/blogs")
	public String blogs(Integer status,Model model,HttpSession session,@RequestParam(value="page",defaultValue="1")Integer page){
		Article article = new Article();
		User user = (User) session.getAttribute(Constant.LOGIN_USER);//获取当前登陆的用户
		article.setAuthor(user);
		if(status!=null || status !=1){
			if(status == 2){
				article.setHot(true);
			}
			if(status == 3){
				article.setStatus(1);
			}
			if(status == 4){
				article.setDeleted(true);
			}
		}
		PageHelper.startPage(page, 3);
		List<Article> list = articleService.queryAll(article);
		PageInfo<Article> pageInfo = new PageInfo<Article>(list,3);
		String pageList = PageHelpUtil.page("blogs", pageInfo, String.valueOf(status));
		model.addAttribute("blogs", list);
		model.addAttribute("pageList", pageList);
		
		return "user-space/blog_list";
	}
	
	// 回显数据
	@RequestMapping("/blog/edit")
	public String edit(Integer id,Model model){
		Article article = articleService.selectByPrimaryKey(id);
		System.out.println(article);
		model.addAttribute("blog", article);
		return "user-space/blog_edit";
	}
	
	//修改和发布文章
	@RequestMapping("/blog/save")
	public String save(Article article,MultipartFile file,HttpServletRequest request){
		// 图片上传
		String upload = FileUploadUtil.upload(request, file);
		if(!upload.equals("")){
			article.setPicture(upload);
		}
		
		if(article.getId() != null){
			System.out.println("==========="+article);
			// 修改文章
			articleService.updateByKey(article);
		}else{
			//发布新文章
			article.setHits(0); // 第一次发布文章 没有点击量
			
			article.setHot(true); // 是否为热门信息
			
			article.setStatus(1); // 是否通过文章审核
			
			article.setDeleted(false); // 文章是否被删除
			
			article.setCreated(new Date()); // 文章创建时间
			
			User user = (User) request.getSession().getAttribute(Constant.LOGIN_USER);
			
			article.setAuthor(user);
			
			System.out.println("_______________________________"+article);
			articleService.save(article);
		}
		return "redirect:/my/blogs?status=1";
	}
	
	// 删除文章
	@RequestMapping("/blog/remove")
	public String remove(Integer id){
		articleService.remove(id);
		return null;
	}
	
	@RequestMapping("/useredit")
	public String useredit(HttpServletRequest request,Model model){
		User loginUser = (User)request.getSession().getAttribute(Constant.LOGIN_USER);
		User user = userService.selevtById(loginUser.getId());
		model.addAttribute("user", user);
		return "user-space/user_edit";
	}
	
	@RequestMapping("/user/save")
	public String usersave(User user){
		userService.updateById(user);
		return "redirect:/my/useredit";
	}
	
	@RequestMapping("/profile/avatar")
	public String profile_avatar(){
		return "/user-space/uploadPic";
	}
	
	@RequestMapping("/blog/updateremove")
	public String updateremove(Integer id,Model model){
		Article article = new Article(id);
		
		article.setDeleted(true);
		articleService.updateByKey(article);
		
		return "redirect:/my/blogs";
	}
	
}
