package com.li.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@ContextConfiguration(locations = "classpath:spring-beans2.xml")
public class RedisJdkJsonTest {
	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;

	@Test
	public void redis_insert() {
		List<User> list = new ArrayList<User>();
		for (int i = 0; i < 100000; i++) {
			list.add(new User(i, StringUtil.generateChineseName() + StringUtil.randomChineseString(2),
					StringUtil.randomChineseString(), "19" + RandomUitl.randomString(9),
					RandomUitl.random(3, 20) + "@qq.com", RandomUitl.randomString(2)));
		}
		long startTime = System.currentTimeMillis();
		for (User user : list) {
			redisTemplate.opsForValue().set("user_"+user.getId(), user);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("采用json方式存储十万条数据所用的时间是："+(endTime-startTime)+",保存数量："+list.size());
	}
}
