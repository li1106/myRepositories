package com.ldd.cms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ldd.cms.domain.Article;
import com.ldd.cms.domain.Category;
import com.ldd.cms.domain.Channel;
import com.ldd.cms.service.ArticleService;
import com.ldd.cms.service.CategoryService;
import com.ldd.cms.service.ChannelService;
import com.ldd.cms.utils.CMSConstant;
import com.ldd.cms.utils.ESUtils;
import com.ldd.cms.vo.ArticlePicVO;

@Controller
public class IndexSearchController {

	@Resource
	private ChannelService channelService;

	@Resource
	private ArticleService articleService;

	@Resource
	private CategoryService categoryService;

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;

	/**
	 * 
	 * @Title: toIndex
	 * @Description: 进入首页
	 * @return
	 * @return: String
	 * @throws InterruptedException
	 */
	@GetMapping({ "/search" })
	public String toIndex(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "5") Integer rows, Integer channelId, String key, Article article, Model model)
			throws InterruptedException {
		// 查询条件 所有显示的文章都是审核过的
		article.setStatus(CMSConstant.ARTICLE_STATUS_CHECKED);

		// 封装到model
		model.addAttribute("article", article);

		// 定义6个线程
		Thread t1 = null;
		Thread t2 = null;
		Thread t3 = null;
		Thread t4 = null;
		Thread t5 = null;
		Thread t6 = null;

		// 线程1 返回所有栏目
		t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// 查询出所有的栏目
				List<Channel> channels = channelService.selectObjects();
				// 存储到Model中
				model.addAttribute("channels", channels);

			}
		});

		// 线程2 热门文章
		t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				AggregatedPage<?> pageInfo = ESUtils.selectObjects(elasticsearchTemplate, Article.class, page, rows,
						new String[] { "title", "content" }, key);
				// 存储的数据
				model.addAttribute("pageInfo", pageInfo);
				model.addAttribute("page", page);
				model.addAttribute("key", key);

			}
		});
		// 线程3 // 查询栏目下的所有分类
		t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				if (channelId != null && channelId != 0) {

					// List<Map> categorys =
					// channelService.selectCategorys(article.getChannelId());
					List<Category> categorys = categoryService.selectObjectsByChannelId(channelId);
					model.addAttribute("categorys", categorys);
				}
			}
		});

		// 线程4 具体栏目或分类下的文章
		t4 = new Thread(new Runnable() {

			@Override
			public void run() {

				List<Map> articles = articleService.getTitles(article);
				model.addAttribute("articles", articles);
			}
		});

		// 线程5 获取最新文章
		t5 = new Thread(new Runnable() {

			@Override
			public void run() {

				int pageSize = 10;
				PageHelper.startPage(0, pageSize);
				List<Map> lasts = articleService.getTitles(null);
				model.addAttribute("lasts", lasts);
			}
		});

		// 线程 6 获取最新图片
		t6 = new Thread(new Runnable() {

			@Override
			public void run() {
				// 引入gson.它可以把java对象转为json,也可以把json转为对象
				// 重新建立查询条件
				Article article2 = new Article();
				article2.setStatus(CMSConstant.ARTICLE_STATUS_CHECKED);
				// 查询栏目为图片的
				article2.setChannelId(9);
				// 获取所有的图片集
				List<Map> maps = articleService.getPics(article2);

				model.addAttribute("pics", maps);

			}
		});

		// 运行线程
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();

		// 让线程都执行完,主线程最后执行
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();

		return "index/index_search";
	}

}
