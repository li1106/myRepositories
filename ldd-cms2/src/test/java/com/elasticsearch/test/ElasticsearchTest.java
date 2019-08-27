package com.elasticsearch.test;


import java.util.List;

import javax.annotation.Resource;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ldd.cms.domain.Article1;
import com.ldd.cms.utils.ESUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class ElasticsearchTest {
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	// 保存数据
	@Test
	public void insert_data(){
		for (int i = 0; i <10; i++) {
			Article1 article = new Article1(i, "ldd_"+i);
			// 创建查询
			IndexQuery query = new IndexQuery();
			query.setId(article.getAid()+"");
			query.setObject(article);
			// 保存
			elasticsearchTemplate.index(query);
		}
	}
	
	// 删除
	@Test
	public void delete_data(){
		elasticsearchTemplate.delete(Article1.class, 1+"");
	}
	
	// 查询
	@Test
	public void select_data(){
		GetQuery query = new GetQuery();
		query.setId("5");
		Article1 article1 = elasticsearchTemplate.queryForObject(query, Article1.class);
		System.out.println(article1);
	}
	
	// 模糊查询
	@Test
	public void select_like_data(){
		// 构建查询对象
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "4");
		// 定义查询对象
		SearchQuery query = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
		// 执行查询
		List<Article1> list = elasticsearchTemplate.queryForList(query, Article1.class);
		for (Article1 article1 : list) {
			System.out.println(article1);
		}
	}
	
	// 高亮查询
	public void selectHighData(){
		AggregatedPage<?> page = ESUtils.selectObjects(elasticsearchTemplate, Article1.class, 1, 3, new String[]{"name"}, "2");
		List<?> list = page.getContent();
		list.forEach(System.out::println);
	}
	
}
