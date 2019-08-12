package com.li.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.li.bean.User;
import com.li.util.RandomUitl;
import com.li.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class RedisHashTest {
	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;

	@Test
	public void redis_insert() {
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		for (int i = 0; i < 100000; i++) {
			map.put("user_"+i,new User(i, StringUtil.generateChineseName() + StringUtil.randomChineseString(2),
					StringUtil.randomChineseString(), "19" + RandomUitl.randomString(9),
					RandomUitl.random(3, 20) + "@qq.com", RandomUitl.randomString(2)));
		}
		long startTime = System.currentTimeMillis();
		redisTemplate.opsForHash().putAll("user", map);
		long endTime = System.currentTimeMillis();
		System.out.println("采用hash方式存储十万条数据所用的时间是："+(endTime-startTime));
	}
}
