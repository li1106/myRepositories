package com.ldd.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldd.cms.dao.CollectsMapper;
import com.ldd.cms.domain.Article;
import com.ldd.cms.domain.Collects;
import com.ldd.cms.service.CollectsServcie;
@Service
public class CollectsServcieImpl implements CollectsServcie {
	@Resource
	private CollectsMapper collectsMapper;

	@Override
	public Collects selectObjectByIdAndArticleId(Integer userId, Integer articleId) {
		// TODO Auto-generated method stub
		return collectsMapper.selectObjectByIdAndArticleId(userId,articleId);
	}

	@Override
	public boolean insertObject(Collects entity) {
		// TODO Auto-generated method stub
		return collectsMapper.insertObject(entity) > 0;
	}

	@Override
	public boolean deleteObject(Collects entity) {
		// TODO Auto-generated method stub
		return collectsMapper.deleteObject(entity) > 0;
	}

	@Override
	public List<Collects> findCollectsById(Integer id) {
		// TODO Auto-generated method stub
		return collectsMapper.findCollectsById(id);
	}

	@Override
	public List<Article> findCollectsByUserId(List<Integer> id) {
		// TODO Auto-generated method stub
		return collectsMapper.findCollectsByUserId(id);
	}

}
