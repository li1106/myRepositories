package com.ldd.cms.kafka;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldd.cms.domain.Article;
import com.ldd.cms.service.ArticleService;
import com.ldd.cms.utils.ESUtils;

public class CmsKafkaListener implements MessageListener<String, String> {
	private ObjectMapper objectMapper = new ObjectMapper();
	@Resource
	private ArticleService articleService;
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		// TODO Auto-generated method stub
		String key = data.key();
		String value = data.value();
		System.out.println("key@@@@="+key);
		System.out.println("value@@@@="+value);
		if(key!=null&&key.contains("ldd_cms_article")){
			try {
				Article article = objectMapper.readValue(value, Article.class);
				System.out.println(article+"=====");
				articleService.insert(article);
				ESUtils.saveObject(elasticsearchTemplate, article.getId(), article);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(key!=null && key.contains("ldd_cms_hit_kafka")){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			articleService.updateHits(Integer.valueOf(value));
		}
	}

}
