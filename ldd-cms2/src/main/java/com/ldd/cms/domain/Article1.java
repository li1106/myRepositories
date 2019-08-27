package com.ldd.cms.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * 
 * @ClassName: Article
 * @Description: 文章
 * @author: charles
 * @date: 2019年7月17日 上午11:10:16
 */
@Document(indexName="ldd_cms_es",type="ldd_cms_es_article1")
public class Article1 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer aid;
	private String name;
	
	public Article1() {
		super();
	}
	public Article1(Integer aid, String name) {
		super();
		this.aid = aid;
		this.name = name;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Article1 [aid=" + aid + ", name=" + name + "]";
	}
	
}