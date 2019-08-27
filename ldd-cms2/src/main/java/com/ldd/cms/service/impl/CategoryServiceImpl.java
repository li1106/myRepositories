package com.ldd.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ldd.cms.dao.CategorysMapper;
import com.ldd.cms.domain.Category;
import com.ldd.cms.service.CategoryService;
import com.ldd.cms.utils.CMSConstant;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private CategorysMapper categorysMapper;

	@Resource
	private RedisTemplate<String, Category> redisTemplate;

	@Override
	public List<Category> selectObjectsByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		List<Category> list = new ArrayList<Category>();
		// 检测redis当中存在栏目的缓存
		if (redisTemplate.hasKey(CMSConstant.REDIS_KEY_CHANNEL_HASH)) {
			// 检测是否存在分类的hash缓存
			if (redisTemplate.opsForHash().hasKey(CMSConstant.REDIS_KEY_CHANNEL_HASH,
					CMSConstant.REDIS_KEY_CHANNEL_HASH_KEY + channelId)) {
				// 从redis当中获取了当前栏目下的所有的分类的信息
				list = (List<Category>) redisTemplate.opsForHash().get(CMSConstant.REDIS_KEY_CHANNEL_HASH, CMSConstant.REDIS_KEY_CHANNEL_HASH_KEY + channelId);
			} else {
				// 从数据库查询当前栏目所有的分类信息
				list = categorysMapper.selectObjectByChannelId(channelId);
				// 存入到redis当中
				redisTemplate.opsForHash().put(CMSConstant.REDIS_KEY_CHANNEL_HASH,
						CMSConstant.REDIS_KEY_CHANNEL_HASH_KEY + channelId, list);
				;
			}
		} else {
			// 从数据库查询当前栏目所有的分类信息
			list = categorysMapper.selectObjectByChannelId(channelId);
			// 存入到redis当中
			redisTemplate.opsForHash().put(CMSConstant.REDIS_KEY_CHANNEL_HASH,
					CMSConstant.REDIS_KEY_CHANNEL_HASH_KEY + channelId, list);
			;
		}
		return list;
	}

}
