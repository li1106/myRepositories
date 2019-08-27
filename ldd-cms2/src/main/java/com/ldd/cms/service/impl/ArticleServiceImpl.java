package com.ldd.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ldd.cms.dao.ArticleMapper;
import com.ldd.cms.domain.Article;
import com.ldd.cms.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Resource
	private ArticleMapper articleMapper;
	

	@Override
	public int insert(Article article) {
		return articleMapper.insert(article);
	}


	@Override
	public Map get(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.get(id);
	}


	@Override
	public List<Map> getTitles(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.getTitles(article);
	}


	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.update(article);
	}


	@Override
	public List<Map> getPics(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.getPics(article);
	}


	@Override
	public boolean deleteArticleByIds(Integer[] aids) {
		// TODO Auto-generated method stub
		return articleMapper.deleteArticleByIds(aids)>0 ;
	}

	
	@Async // 线程池的使用
	@Override
	public int updateHits(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("service "+Thread.currentThread().getName()+"@@"+Thread.currentThread().getId());
		return articleMapper.updateHits(id);
	}

}
