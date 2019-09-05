package com.li.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.li.entity.Student;
import com.li.mapper.StudentMapper;
import com.li.service.StudentService;

@Service(interfaceClass = StudentService.class)
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentMapper stuMapper;

	@Override
	public PageInfo<Student> list(Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(stuMapper.list());
	}

}
