package com.ldd.cms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ldd.cms.dao.ChannelMapper;
import com.ldd.cms.domain.Channel;
import com.ldd.cms.service.ChannelService;
import com.ldd.cms.utils.CMSConstant;

@Service
public class ChannelServiceImpl implements ChannelService {
	
	@Resource
	private ChannelMapper channelMapper;
	
	@Resource
	private RedisTemplate<String,Channel> redisTemplate;

	@Override
	public List<Map> selectChannels() {
		return channelMapper.selectChannels();
	}

	@Override
	public List<Map> selectCategorys(Integer channelId) {
		return channelMapper.selectCategorys(channelId);
	}

	@Override
	public List<Channel> selectObjects() {
		// TODO Auto-generated method stub
		List<Channel> list = new ArrayList<Channel>();
		if(redisTemplate.hasKey(CMSConstant.REDIS_KEY_CHANNEL)){
			list = redisTemplate.opsForList().range(CMSConstant.REDIS_KEY_CHANNEL, 0, -1);
		}else{
			list = channelMapper.selectObjects();
			redisTemplate.opsForList().leftPushAll(CMSConstant.REDIS_KEY_CHANNEL, list);
			redisTemplate.expire(CMSConstant.REDIS_KEY_CHANNEL, 1, TimeUnit.HOURS);
		}
		return list;
	}

}
