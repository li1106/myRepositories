package com.ldd.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldd.cms.dao.CommentMapper;
import com.ldd.cms.domain.Comment;
import com.ldd.cms.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentMapper commentMapper;
	@Override
	public int insert(Comment comment) {
		return commentMapper.insert(comment);
	}

	@Override
	public List<Map> selects(Integer articleId) {
		// TODO Auto-generated method stub
		return commentMapper.selects(articleId);
	}

}
