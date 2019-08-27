package com.ldd.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldd.cms.domain.Article;
import com.ldd.cms.domain.Collects;

public interface CollectsMapper {

	Collects selectObjectByIdAndArticleId(@Param("userId")Integer userId, @Param("articleId")Integer articleId);

	int insertObject(Collects entity);

	int deleteObject(Collects entity);

	List<Collects> findCollectsById(Integer id);

	List<Article> findCollectsByUserId(List<Integer> id);

}
