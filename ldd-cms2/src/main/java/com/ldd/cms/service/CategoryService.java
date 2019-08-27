package com.ldd.cms.service;

import java.util.List;

import com.ldd.cms.domain.Category;

public interface CategoryService {
	
	List<Category> selectObjectsByChannelId(Integer channelId);
}
