package com.li.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldd.cms.domain.Article;
import com.ldd.cms.domain.Category;
import com.ldd.cms.domain.Channel;
import com.ldd.cms.service.CategoryService;
import com.ldd.cms.service.ChannelService;
import com.ldd.cms.utils.DateUtil;
import com.ldd.cms.utils.IKWord;
import com.ldd.cms.utils.RandomUitl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-beans.xml","classpath:spring-producer.xml"})
public class KafkaSendArticle {
	
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Resource
	private ChannelService channelService;

	@Resource
	private CategoryService categoryService;



	@Test
	public void test_import() throws IOException {
		// 指定目录 创建文件
		File src = new File("D:\\1703ajsoup");
		// 遍历目录中的所有文件
		File[] listFiles = src.listFiles();
		// 遍历目录中的文件
		for (int index = 0; index < listFiles.length; index++) {
			// 获取具体文件对象
			File file = listFiles[index];
			// 创建文件的输入流
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf8"));
			// 定义缓冲区
			StringBuffer sb = new StringBuffer();
			// 定义读取的内容
			String content = null;
			// 循环读取
			while ((content = br.readLine()) != null) {
				// 加入sb中
				sb.append(content);
			}
			// 读取的文章内容
			String article_content = sb.toString();
			// 获取文章的标题
			String article_title = file.getName().substring(0, file.getName().length() - 4);

			// 创建一个文章对象
			Article article = new Article();
			// 设置文章的标题
			article.setTitle(article_title);
			// 设置文章的内容
			article.setContent(article_content);

			// 随机点击量
			article.setHits(RandomUitl.random(0, 10000));

			// 随机时间
			article.setCreated(DateUtil.randomDate(new Date(2000, 1, 12))); // 每个文章 +7个小时

			// 随机热门
			article.setHot(RandomUitl.random(0, 1));

			// 查询出所有栏目
			// 所有栏目
			List<Channel> channels = channelService.selectObjects();
			// 随机生成一个栏目集合的下标
			int channel_index = RandomUitl.random(0, channels.size() - 1);
			// 获取栏目对象
			Channel channel = channels.get(channel_index);

			// 随机的栏目id
			article.setChannelId(channel.getId());

			// 分类随机,建立在栏目的基础上
			// 查出当前栏目下面的所有分类信息
			List<Category> categories = categoryService.selectObjectsByChannelId(channel.getId());
			//栏目下面 判断是否有分类
			if (categories != null && categories.size() > 0) {
				// 随机生成一个分类集合的下标
				int category_index = RandomUitl.random(0, categories.size() - 1);
				// 获取分类对象
				Category category = categories.get(category_index);
				// 随机栏目下面的随机的分类id
				article.setCategoryId(category.getId());
			}

			// 设置删除的状态
			article.setDeleted(0);
			// 设置状态
			article.setStatus(1);
			
			String descriptions	 =null;
			//设置文章的描述词，统计的设置
			List<Entry<String, Integer>> list = IKWord.order(IKWord.count(new HashMap<String,Integer>(), article_content));
            if(list!=null && list.size()>0) {
            	if(list.size()>3) {
            		descriptions=list.get(0).getKey()+","+list.get(1).getKey()+","+list.get(2).getKey();
            	}else {
            		for(int j=0;j<list.size();j++) {
            			descriptions+=list.get(j).getKey();
            			if(list.size()!=j) {
            				descriptions+=",";
            			}
            		}
            	}
            }
			System.out.println(descriptions);
			//设置描述信息
			article.setDescriptions(descriptions);
			
			//转换json的字符串
			String json = objectMapper.writeValueAsString(article);
			//发送kafka操作
			kafkaTemplate.sendDefault("ldd_cms_article_"+System.currentTimeMillis(), json);
		}

	}

}
