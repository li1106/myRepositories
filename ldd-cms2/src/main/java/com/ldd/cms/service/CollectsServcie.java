package com.ldd.cms.service;

import java.util.List;

import com.ldd.cms.domain.Article;
import com.ldd.cms.domain.Collects;

public interface CollectsServcie {

	Collects selectObjectByIdAndArticleId(Integer id, Integer id2);

	boolean insertObject(Collects entity);

	boolean deleteObject(Collects entity);

	List<Collects> findCollectsById(Integer id);

	List<Article> findCollectsByUserId(List<Integer> ids);

}
