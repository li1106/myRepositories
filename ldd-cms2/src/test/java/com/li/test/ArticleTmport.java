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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ldd.cms.domain.Article;
import com.ldd.cms.domain.Category;
import com.ldd.cms.domain.Channel;
import com.ldd.cms.service.ArticleService;
import com.ldd.cms.service.CategoryService;
import com.ldd.cms.service.ChannelService;
import com.ldd.cms.utils.DateUtil;
import com.ldd.cms.utils.IKWord;
import com.ldd.cms.utils.RandomUitl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ArticleTmport {
	@Resource
	private ChannelService channelService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private ArticleService articleService;

	@Test
	public void test_import() throws IOException {
		// 指定目录 创建文件
		File src = new File("D:\\1703ajsoup");
		// 遍历目录中的所有文件
		File[] files = src.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 获取具体的文件对象
			File file = files[i];
			// 创建文件的输入流
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf8"));
			// 定义缓冲区
			StringBuffer sb = new StringBuffer();
			// 定义读取的内容
			String context = null;
			// 循环读取
			while ((context = br.readLine()) != null) {
				// 加入到缓冲区当中
				sb.append(context);
			}
			// 读取的文章内容
			String article_context = sb.toString();
			// 获取文章的标题 文件的名字 - ".txt"四个长度 就是标题
			String article_title = file.getName().substring(0, file.getName().length() - 4);
			// 创建文章的对象
			Article article = new Article();
			// 文章的标题
			article.setTitle(article_title);
			// 设置文章的内容
			article.setContent(article_context);
			// 随机的点击量
			article.setHits(RandomUitl.random(0, 10000));
			// 随机时间 每个文章 +7个小时
			article.setCreated(DateUtil.randomDate(new Date(2000, 1, 12)));
			// 随机热门
			article.setHot(RandomUitl.random(0, 1));
			// 查询出所有的栏目
			// 所有栏目
			List<Channel> channels = channelService.selectObjects();
			// 随机生成一个集合的下标 下标从0开始
			int channel_index = RandomUitl.random(0, channels.size() - 1);
			// 获取栏目对象
			Channel channel = channels.get(channel_index);
			// 随机的栏目id
			article.setChannelId(channel.getId());
			// 分类随机 建立在栏目的基础上
			// 查出当前栏目下面的所有分类信息
			List<Category> categories = categoryService.selectObjectsByChannelId(channel_index);
			// 栏目下面 判断是否有分类
			if (categories.size() > 0 && categories != null) {
				// 随机生成一个分类集合的下标
				int category_index = RandomUitl.random(0, categories.size() - 1);
				// 获取分类的对象
				Category category = categories.get(category_index);
				// 随机栏目下面的随机的分类的id
				article.setCategoryId(category.getId());
			}
			// 设置删除的状态
			article.setDeleted(0);
			// 设置状态
			article.setStatus(1);
			String descriptions=null;
			// 设置文章的描述词  统计的设置
			List<Entry<String, Integer>> list = IKWord.order(IKWord.count(new HashMap<String, Integer>(), article_context));
			if(list !=null && list.size()>0){
				if(list.size()>3){
					descriptions=list.get(0).getKey()+","+list.get(1).getKey()+list.get(2).getKey();
				}else{
					for(int j=0;j<list.size();j++){
						descriptions=list.get(j).getKey();
						if(list.size()!=j){
							descriptions+=",";
						}
					}
				}
			}
			System.out.println("====="+descriptions);
			article.setDescriptions(descriptions);
			// 保存对象
			articleService.insert(article);
		}
	}
}