package com.ldd.cms.domain;

import java.io.Serializable;
import java.util.Date;

public class Collects implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private User user; // user_id
	private Article article; // article_id
	private Date created;
	public Collects(Integer id, User user, Article article, Date created) {
		super();
		this.id = id;
		this.user = user;
		this.article = article;
		this.created = created;
	}
	public Collects() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "Collects [id=" + id + ", user=" + user + ", article=" + article + ", created=" + created + "]";
	}
	
	

}
