package com.ldd.cms.dao;

import java.util.List;

import com.ldd.cms.domain.Category;

/**
 * 分类信息的接口
 * @author 李大大
 *
 */
public interface CategorysMapper {
	List<Category> selectObjectByChannelId(Integer channelId);
}
