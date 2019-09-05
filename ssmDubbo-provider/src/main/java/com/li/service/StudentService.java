package com.li.service;

import com.github.pagehelper.PageInfo;
import com.li.entity.Student;

public interface StudentService {
	PageInfo<Student> list (Integer pageNum,Integer pageSize);
}
